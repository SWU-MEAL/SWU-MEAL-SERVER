package com.swumeal.app.domain.menu.service;

import com.swumeal.app.domain.menu.domain.MenuDAO;
import com.swumeal.app.domain.menu.domain.MenuItemVO;
import com.swumeal.app.domain.menu.domain.MenuVO;
import com.swumeal.app.domain.menu.dto.DailyMenuDto;
import com.swumeal.app.domain.menu.dto.DailyMenuListDto;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import com.swumeal.app.domain.menu.dto.MenuListByTimeDto;
import com.swumeal.app.domain.menu.dto.request.DateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class MenuReadServiceTest {
    @Autowired
    MenuReadService menuReadService;

    @Autowired
    MenuDAO menuDAO;

    final ArrayList<MenuVO> menuList = new ArrayList<>();
    final String TYPE_A = "샬롬", TYPE_B = "교직원";
    final String TIME_B = "조식", TIME_L = "중식", TIME_D = "석식";
    final String CORNER_A = "한식", CORNER_B = "일품", CORNER_C = "스낵";
    final String MENU = "메뉴";

    void addMockData(String date) {
        // Add Menu
        for (int i = 0; i < 6; i++) {
            MenuVO menuVO = new MenuVO();
            menuVO.setMealDate(date);

            menuList.add(menuVO);
        }

        menuList.get(0).setMealType(TYPE_A);
        menuList.get(0).setTime(TIME_B);

        menuList.get(1).setMealType(TYPE_B);
        menuList.get(1).setTime(TIME_L);

        for (int i = 2; i < 5; i++) {
            String corner = i == 2 ? CORNER_A : (i == 3 ? CORNER_B : CORNER_C);
            menuList.get(i).setMealType(TYPE_A);
            menuList.get(i).setTime(TIME_L);
            menuList.get(i).setCorner(corner);
        }

        menuList.get(5).setMealType(TYPE_A);
        menuList.get(5).setTime(TIME_D);

        // Add Menu Items
        for (int i = 0; i < 6; i++) {
            MenuVO menuVO = menuList.get(i);
            menuDAO.save(menuVO);

            for (int l = 0; l < 7; l++) {
                String corner = menuVO.getCorner() == null ? "" : menuVO.getCorner();
                String item = MENU + l + menuVO.getTime() + menuVO.getMealType() + corner;
                menuDAO.save(new MenuItemVO(menuVO, item));
            }
        }
    }

    @Test
    @DisplayName("findByDate service test")
    void findByDate() {
        // given
        String date = "2025-10-15";
        DateRequestDto dto = new DateRequestDto(date);
        addMockData(date);

        // when
        MenuListByDateDto result = menuReadService.findByDate(dto);
        ArrayList<DailyMenuListDto> list = result.getResult();

        // then
        log.info(result.toString());

        assertThat(result.getDate()).as("[데이터 조회 실패] 날짜 불일치").isEqualTo(date);
        assertThat(list.size()).as("[데이터 조회 실패] 데이터 시간별 분류 총 건수 불일치").isEqualTo(3);
        assertThat(list.get(1).getMenuList().size()).as("[데이터 조회 실패] 데이터 식단별 분류 총 건수 불일치").isEqualTo(4);
        assertThat(list.get(2).getMenuList().get(0).getType()).as("[데이터 조회 실패] 데이터 식단별 분류 총 건수 불일치").isEqualTo(menuList.get(5).getMealType());
    }

    @Test
    @DisplayName("findByTime service test")
    void findByTime() {
        // given
        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        log.info(date);
        String time = "d";
        String item = MENU + "2" + TIME_D + TYPE_A;
        addMockData(date);

        // when
        MenuListByTimeDto result = menuReadService.findByTime(time);
        ArrayList<DailyMenuDto> list = result.getResult();

        // then
        log.info(result.toString());

        assertThat(result.getDate()).as("[데이터 조회 실패] 날짜 불일치").isEqualTo(date);
        assertThat(result.getTime()).as("[데이터 조회 실패] 시간 불일치").isEqualTo(TIME_D);
        assertThat(list.size()).as("[데이터 조회 실패] 데이터 총 건수 불일치").isEqualTo(1);
        assertThat(list.get(0).getType()).as("[데이터 조회 실패] 식단 타입 불일치").isEqualTo(TYPE_A);
        assertThat(list.get(0).getItems().get(2)).as("[데이터 조회 실패] 데이터 식단별 분류 총 건수 불일치").isEqualTo(item);
    }
}