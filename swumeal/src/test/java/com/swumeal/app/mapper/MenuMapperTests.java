package com.swumeal.app.mapper;

import com.swumeal.app.domain.menu.domain.MenuItemVO;
import com.swumeal.app.domain.menu.domain.MenuVO;
import com.swumeal.app.domain.menu.dto.MenuDTO;
import com.swumeal.app.domain.menu.vo.MenuDataVo;
import com.swumeal.app.domain.model.MealTypeEnum;
import com.swumeal.app.domain.model.TimeEnum;
import com.swumeal.app.global.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class MenuMapperTests {

    @Autowired
    MenuMapper menuMapper;

    @Test
    public void selectTest() {
//        assertThat(menuMapper.select()).hasSize(1);
        menuMapper.select().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    public void selectAllTest() {
//        assertThat(menuMapper.selectAll()).hasSize(2);
        menuMapper.selectAll().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    @DisplayName("Insert menu 테스트")
    public void insertMenu() {
        // given
        LocalDate date = LocalDate.of(2023, 10, 24);
        TimeEnum time = TimeEnum.LUNCH;
        MealTypeEnum type = MealTypeEnum.STAFF;
        MenuDataVo dataVo = MenuDataVo.builder()
                .date(date)
                .time(time)
                .type(type)
                .corner(null)
                .build();
        MenuVO vo = new MenuVO(dataVo);

        // when
        Long id = menuMapper.insertMenu(vo);

        // then
        assertThat(id).as("id가 제대로 설정되지 않음").isEqualTo(1);
    }

    @Test
    @DisplayName("Insert menu-item 테스트")
    public void insertMenuItem() {
        // given
        LocalDate date = LocalDate.of(2023, 10, 24);
        TimeEnum time = TimeEnum.LUNCH;
        MealTypeEnum type = MealTypeEnum.STAFF;
        MenuDataVo dataVo = MenuDataVo.builder()
                .date(date)
                .time(time)
                .type(type)
                .corner(null)
                .build();
        MenuVO menuVo = new MenuVO(dataVo);
        menuMapper.insertMenu(menuVo);
        MenuItemVO itemVo = new MenuItemVO(menuVo, "샌드위치");

        // when
        menuMapper.insertMenuItem(itemVo);

        // then
        assertThat(itemVo.getId()).as("id가 제대로 설정되지 않음").isGreaterThan(0);
    }
}
