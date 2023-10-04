package com.swumeal.app.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class DailyMenuDto {
    private Long menuId;
    private String type;
    private String corner;
    private ArrayList<String> items;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n")
                .append(menuId)
                .append(", ")
                .append(type)
                .append(", ")
                .append(corner)
                .append("\n");

        for (String item : items)
            builder.append(item).append(" ");

        return builder.append("\n}").toString();
    }
}
