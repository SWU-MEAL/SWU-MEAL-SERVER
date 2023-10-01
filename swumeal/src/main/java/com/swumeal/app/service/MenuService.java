package com.swumeal.app.service;

import com.swumeal.app.dao.MenuDAO;
import com.swumeal.app.domain.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDAO menuDAO;

    public List<MenuDTO> todaygetList(){
        return menuDAO.find();
    }

    public List<MenuDTO> getListAll(){
        return menuDAO.findAll();
    }
}
