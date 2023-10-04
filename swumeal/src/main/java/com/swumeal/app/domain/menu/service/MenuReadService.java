package com.swumeal.app.domain.menu.service;

import com.swumeal.app.dao.MenuDAO;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import com.swumeal.app.domain.menu.dto.MenuListByTimeDto;
import com.swumeal.app.domain.menu.dto.request.DateRequestDto;
import com.swumeal.app.domain.menu.error.exception.MenuApiException;
import com.swumeal.app.domain.model.TimeEnum;
import com.swumeal.app.global.error.GlobalErrorCode;
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

    public MenuListByTimeDto findByTime(String timeStr) {
        TimeEnum time;

        // time 값 검사
        switch (timeStr) {
            case "b", "breakfast" -> time = TimeEnum.BREAKFAST;
            case "l", "lunch" -> time = TimeEnum.LUNCH;
            case "d", "dinner" -> time = TimeEnum.DINNER;
            default -> time = null;
        }

        if (time == null)
            throw new MenuApiException(GlobalErrorCode.INVALID_TIME_FORMAT);

        return menuDAO.findByTime(time.getKey());
    }
}
