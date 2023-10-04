package com.swumeal.app.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class DailyMenuListDto {
    private String time;
    private ArrayList<DailyMenuDto> menuList;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n")
                .append(time)
                .append("\n");

        for (DailyMenuDto menu : menuList)
            builder.append(menu.toString());

        return builder.append("\n}").toString();
    }
}
