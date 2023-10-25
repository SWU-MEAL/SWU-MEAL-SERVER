package com.swumeal.app.domain.menu.service;

import com.swumeal.app.domain.menu.domain.MenuDAO;
import com.swumeal.app.domain.menu.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDAO menuDAO;

    public List<MenuDTO> todaygetList() {
        return menuDAO.find();
    }

    public List<MenuDTO> getListAll() {
        return menuDAO.findAll();
    }
}
