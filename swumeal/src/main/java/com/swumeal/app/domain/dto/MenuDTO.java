package com.swumeal.app.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class MenuDTO {
    private Long id;
    private String mealDate;
    private String mealType;
    private String corner;
    private String time;
    private String menuName;
}
