package com.swumeal.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CornerEnum {
    KOREAN("한식", 6),
    SPECIAL("일품", 5),
    SNACK("스낵", 4);

    private final String key;
    private final int rowCount;
}
