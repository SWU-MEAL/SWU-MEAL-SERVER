package com.swumeal.app.domain.menu.service;

import com.swumeal.app.dao.MenuDAO;
import com.swumeal.app.domain.menu.dto.DateRequestDto;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuReadService {
    private final MenuDAO menuDAO;

    public MenuListByDateDto findByDate(DateRequestDto date) {
        return menuDAO.findByDate(date.getDate());
    }
}
