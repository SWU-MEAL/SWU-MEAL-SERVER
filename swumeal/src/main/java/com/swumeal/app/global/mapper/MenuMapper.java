package com.swumeal.app.global.mapper;

import com.swumeal.app.domain.menu.domain.MenuItemVO;
import com.swumeal.app.domain.menu.domain.MenuVO;
import com.swumeal.app.domain.menu.dto.MenuDTO;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import com.swumeal.app.domain.menu.dto.MenuListByTimeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    // menu 저장
    Long insertMenu(MenuVO menuVO);

    // menu item 저장
    void insertMenuItem(MenuItemVO menuItemVO);

    //  오늘 날짜 슈밥 조회
    List<MenuDTO> select();

    // 이번주 슈밥 조회
    List<MenuDTO> selectAll();

    // 날짜 기준 슈밥 조회
    MenuListByDateDto selectByDate(String date);

    // 시간 기준 슈밥 조회
    MenuListByTimeDto selectByTime(String time);
}
