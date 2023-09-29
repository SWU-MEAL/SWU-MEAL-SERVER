package com.swumeal.app.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MenuVO {
    private Long id;
    private String mealDate;
    private String mealType;
    private String corner;
    private String time;
}
