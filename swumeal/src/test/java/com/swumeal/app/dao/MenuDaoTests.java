package com.swumeal.app.dao;

import com.swumeal.app.domain.dto.MenuDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MenuDaoTests {
    @Autowired
    private MenuDAO menuDAO;

    @Test
    public void findTest(){
        menuDAO.find().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    public void findAllTest() {
        menuDAO.findAll().stream().map(MenuDTO::toString).forEach(log::info);
    }

}
