package com.swumeal.app.mapper;

import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {

//    조회

    // menu 저장
    void insertMenu(MenuVO menuVO);

    // menu item 저장
    void insertMenuItem(MenuItemVO menuItemVO);
}
