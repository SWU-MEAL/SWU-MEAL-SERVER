package com.swumeal.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeEnum {
    BREAKFAST("조식", 3),
    LUNCH("중식", 9),
    DINNER("석식", 25);

    private final String key;
    private final int dormStartRow;
}
