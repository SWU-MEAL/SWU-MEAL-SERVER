package com.swumeal.app.mapper;

import com.swumeal.app.domain.dto.MenuDTO;
import com.swumeal.app.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class MenuMapperTests {

    @Autowired
    MenuMapper menuMapper;

    @Test
    public void selectTest(){
//        assertThat(menuMapper.select()).hasSize(1);
        menuMapper.select().stream().map(MenuDTO::toString).forEach(log::info);
    }

    @Test
    public void selectAllTest(){
//        assertThat(menuMapper.selectAll()).hasSize(2);
        menuMapper.selectAll().stream().map(MenuDTO::toString).forEach(log::info);
    }
}
