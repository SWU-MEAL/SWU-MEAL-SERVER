package com.swumeal.app.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class MenuListByDateDto {
    private String date;
    private ArrayList<DailyMenuListDto> result;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n")
                .append(date)
                .append("\n");

        for (DailyMenuListDto list : result)
            builder.append(list.toString());


        return builder.append("\n}").toString();
    }
}
