package com.swumeal.app.domain.menu.vo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class StaffMenuVo {
    private final Date date;
    private final ArrayList<String> items;

    public StaffMenuVo(Date date, ArrayList<String> items) {
        this.date = date;
        this.items = items;
    }
}
