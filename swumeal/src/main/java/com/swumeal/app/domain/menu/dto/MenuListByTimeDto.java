package com.swumeal.app.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class MenuListByTimeDto {
    private String date;
    private String time;
    private ArrayList<DailyMenuDto> result;
}
