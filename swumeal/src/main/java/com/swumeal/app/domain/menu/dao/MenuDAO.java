package com.swumeal.app.domain.menu.dao;

import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import com.swumeal.app.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
