package com.swumeal.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeEnum {
    BREAKFAST("조식"),
    LUNCH("중식"),
    DINNER("석식");

    private final String key;
}
