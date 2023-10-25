package com.swumeal.app.service;

import com.swumeal.app.domain.menu.dto.MenuDTO;
import com.swumeal.app.domain.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void todaygetListTest() {
        menuService.todaygetList().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    public void getListAllTest() {
        menuService.getListAll().stream().map(MenuDTO::toString).forEach(log::info);
    }
}
