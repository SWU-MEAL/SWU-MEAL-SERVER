package com.swumeal.app.domain.menu.vo;

import com.swumeal.app.domain.model.CornerEnum;
import com.swumeal.app.domain.model.MealTypeEnum;
import com.swumeal.app.domain.model.TimeEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class MenuDataVo {
    private final Date date;
    private final TimeEnum time;
    private final MealTypeEnum type;
    private final CornerEnum corner;
    private final ArrayList<String> items;

    @Builder
    public MenuDataVo(Date date, TimeEnum time, MealTypeEnum type, CornerEnum corner, ArrayList<String> items) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.corner = corner;
        this.items = items;
    }
}
