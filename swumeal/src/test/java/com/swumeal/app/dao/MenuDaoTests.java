package com.swumeal.app.dao;

import com.swumeal.app.domain.dto.MenuDTO;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class MenuDaoTests {
    @Autowired
    private MenuDAO menuDAO;

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

}
