package com.swumeal.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CornerEnum {
    KOREAN("한식"),
    SPECIAL("일품"),
    SNACK("스낵");

    private final String key;
}
