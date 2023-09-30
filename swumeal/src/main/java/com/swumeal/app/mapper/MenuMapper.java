package com.swumeal.app.mapper;

import com.swumeal.app.domain.dto.MenuDTO;
import com.swumeal.app.domain.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

//  오늘 날짜 슈밥 조회
    public List<MenuDTO> select();
// 이번주 슈밥 조회
    public List<MenuDTO> selectAll();
}
