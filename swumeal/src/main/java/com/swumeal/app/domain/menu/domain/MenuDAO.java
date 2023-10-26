package com.swumeal.app.domain.menu.domain;

import com.swumeal.app.domain.menu.dto.MenuDTO;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import com.swumeal.app.domain.menu.dto.MenuListByTimeDto;
import com.swumeal.app.global.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuDAO {
    private final MenuMapper menuMapper;

    // 메뉴 저장
    public void save(MenuVO menuVO) {
        menuMapper.insertMenu(menuVO);
    }

    // 메뉴 아이템 저장
    public void save(MenuItemVO menuItemVO) {
        menuMapper.insertMenuItem(menuItemVO);
    }

    // 오늘 슈밥
    public List<MenuDTO> find() {
        return menuMapper.select();
    }

    // 이번 주 슈밥
    public List<MenuDTO> findAll() {
        return menuMapper.selectAll();
    }

    // 날짜 기준 슈밥
    public MenuListByDateDto findByDate(String date) {
        return menuMapper.selectByDate(date);
    }

    // 시간 기준 슈밥
    public MenuListByTimeDto findByTime(String time) {
        return menuMapper.selectByTime(time);
    }
}
