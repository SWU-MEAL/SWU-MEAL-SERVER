package com.swumeal.app.mapper;

import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import com.swumeal.app.domain.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    // menu 저장
    void insertMenu(MenuVO menuVO);

    // menu item 저장
    void insertMenuItem(MenuItemVO menuItemVO);

//  오늘 날짜 슈밥 조회
    public List<MenuDTO> select();
// 이번주 슈밥 조회
    public List<MenuDTO> selectAll();
  
}
