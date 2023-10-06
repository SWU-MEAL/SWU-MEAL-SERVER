package com.swumeal.app.domain.vo;

import com.swumeal.app.domain.menu.vo.MenuDataVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

    public MenuVO(MenuDataVo vo) {
        this.mealDate = vo.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ISO_DATE);
        this.mealType = vo.getType().getKey();
        this.corner = vo.getCorner() == null ? null : vo.getCorner().getKey();
        this.time = vo.getTime().getKey();
    }
}
