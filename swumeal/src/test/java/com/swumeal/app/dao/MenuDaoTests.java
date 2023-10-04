package com.swumeal.app.dao;

import com.swumeal.app.domain.dto.MenuDTO;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import com.swumeal.app.domain.menu.dto.MenuListByTimeDto;
import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class MenuDaoTests {
    @Autowired
    private MenuDAO menuDAO;

    final ArrayList<MenuVO> menuList = new ArrayList<>();
    final String DATE = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    final String TYPE_A = "샬롬", TYPE_B = "교직원";
    final String TIME_B = "조식", TIME_L = "중식", TIME_D = "석식";
    final String CORNER_A = "한식", CORNER_B = "일품", CORNER_C = "스낵";
    final String MENU = "메뉴";

    @BeforeEach
    void addMockData() {
        // Add Menu
        for (int i = 0; i < 6; i++) {
            MenuVO menuVO = new MenuVO();
            menuVO.setMealDate(DATE);

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
    public void findTest() {
        menuDAO.find().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    public void findAllTest() {
        menuDAO.findAll().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    @DisplayName("findByDate 테스트")
    public void findByDate() {
        // given
        String dateStr = "2023-10-04";

        // when
        MenuListByDateDto menuListByDateDto = menuDAO.findByDate(dateStr);

        // then
        assertThat(menuListByDateDto.getDate().contains(dateStr)).as("[데이터 조회 실패] 날짜 불일치").isTrue();
        log.info(menuListByDateDto.toString());
    }

    @Test
    @DisplayName("findByTime 테스트")
    void findByTime() {
        // given
        String time = "중식";

        // when
        MenuListByTimeDto menuListByTimeDto = menuDAO.findByTime(time);

        // then
        log.info(DATE);
        log.info(menuListByTimeDto.getDate());
        log.info(menuListByTimeDto.toString());
        
        assertThat(menuListByTimeDto.getDate().contains(DATE)).as("[데이터 조회 실패] 날짜 불일치").isTrue();
        assertThat(menuListByTimeDto.getTime()).as("[데이터 조회 실패] 시간 불일치").isEqualTo(time);
        assertThat(menuListByTimeDto.getResult().get(2).getCorner()).as("[데이터 조회 실패] CORNER 값 불일치").isEqualTo(CORNER_A);
    }
}
