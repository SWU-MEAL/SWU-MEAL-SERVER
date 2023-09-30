package com.swumeal.app.dao;

import com.swumeal.app.domain.dto.MenuDTO;
import com.swumeal.app.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuDAO {
    private final MenuMapper menuMapper;

//    오늘슈밥
    public List<MenuDTO> find(){
        return menuMapper.select();
    }

//    이번주 슈밥
    public List<MenuDTO> findAll(){
        return menuMapper.selectAll();
    }
}
