package com.swumeal.app.dao;

import com.swumeal.app.domain.dto.MenuDTO;
import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import com.swumeal.app.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuDAO {
    private final MenuMapper menuMapper;

    public void save(MenuVO menuVO) {
        menuMapper.insertMenu(menuVO);
    }

    public void save(MenuItemVO menuItemVO) {
        menuMapper.insertMenuItem(menuItemVO);
    }

    //    오늘슈밥
    public List<MenuDTO> find() {
        return menuMapper.select();
    }

    //    이번주 슈밥
    public List<MenuDTO> findAll() {
        return menuMapper.selectAll();
    }
}
