package com.swumeal.app.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MenuItemVO {
    private Long id;
    private Long menuId;
    private String menuName;

    public MenuItemVO(MenuVO menuVO, String menuName) {
        this.menuId = menuVO.getId();
        this.menuName = menuName;
    }
}
