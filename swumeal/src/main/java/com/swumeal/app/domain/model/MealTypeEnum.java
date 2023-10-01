package com.swumeal.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MealTypeEnum {
    DORMITORY("샬롬"),
    STAFF("교직원");

    private final String key;
}
