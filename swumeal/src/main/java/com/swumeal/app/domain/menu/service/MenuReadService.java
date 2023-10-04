package com.swumeal.app.domain.menu.service;

import com.swumeal.app.dao.MenuDAO;
import com.swumeal.app.domain.menu.dto.DateRequestDto;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuReadService {
    private final MenuDAO menuDAO;

    public MenuListByDateDto findByDate(@RequestParam @Valid DateRequestDto date) {
        return menuDAO.findByDate(date.getDate());
    }
}
