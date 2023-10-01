package com.swumeal.app.domain.vo;

import com.swumeal.app.domain.model.MealTypeEnum;
import com.swumeal.app.domain.model.TimeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class MenuVO {
    private Long id;
    private String mealDate;
    private String mealType;
    private String corner;
    private String time;

    public MenuVO(Date mealDate) {
        this.mealDate = mealDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ISO_DATE);
        mealType = MealTypeEnum.STAFF.getKey();
        corner = null;
        time = TimeEnum.LUNCH.getKey();
    }
}
