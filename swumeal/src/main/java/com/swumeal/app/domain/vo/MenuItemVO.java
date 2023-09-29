package com.swumeal.app.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MenuItemVO {
    private Long id;
    private String menuId;
    private String menuName;
}
