package com.swumeal.app.global.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class ProcessedMenuFileDto {
    private LocalDate date;
    private ArrayList<String> items;

    public ProcessedMenuFileDto(Date date, ArrayList<String> items) {
        this.date = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        this.items = items;
    }
}
