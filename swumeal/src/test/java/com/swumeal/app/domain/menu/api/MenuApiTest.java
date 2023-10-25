package com.swumeal.app.domain.menu.api;

import com.swumeal.app.dao.MenuDAO;
import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MenuAPI test")
@Transactional
class MenuApiTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MenuDAO menuDAO;

    final ArrayList<MenuVO> menuList = new ArrayList<>();
    final String DATE = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    final String TYPE_A = "샬롬", TYPE_B = "교직원";
    final String TIME_B = "조식", TIME_L = "중식", TIME_D = "석식";
    final String CORNER_A = "한식", CORNER_B = "일품", CORNER_C = "스낵";
    final String MENU = "메뉴";

    @BeforeEach
    void addMockData() {
        // Add Menu
        for (int i = 0; i < 6; i++) {
            MenuVO menuVO = new MenuVO();
            menuVO.setMealDate(DATE);

            menuList.add(menuVO);
        }

        menuList.get(0).setMealType(TYPE_A);
        menuList.get(0).setTime(TIME_B);

        menuList.get(1).setMealType(TYPE_B);
        menuList.get(1).setTime(TIME_L);

        for (int i = 2; i < 5; i++) {
            String corner = i == 2 ? CORNER_A : (i == 3 ? CORNER_B : CORNER_C);
            menuList.get(i).setMealType(TYPE_A);
            menuList.get(i).setTime(TIME_L);
            menuList.get(i).setCorner(corner);
        }

        menuList.get(5).setMealType(TYPE_A);
        menuList.get(5).setTime(TIME_D);

        // Add Menu Items
        for (int i = 0; i < 6; i++) {
            MenuVO menuVO = menuList.get(i);
            menuDAO.save(menuVO);

            for (int l = 0; l < 7; l++) {
                String corner = menuVO.getCorner() == null ? "" : menuVO.getCorner();
                String item = MENU + l + menuVO.getTime() + menuVO.getMealType() + corner;
                menuDAO.save(new MenuItemVO(menuVO, item));
            }
        }
    }

    @Test
    @DisplayName("findByDate API test")
    void findByDate() throws Exception {
        // given
        String query = "date";
        String url = "/v1/menu";

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param(query, DATE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.date").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("findByTime API test")
    void findByTime() throws Exception {
        // given
        String query = "time";
        String time = "l";
        String url = "/v1/menu/today";

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param(query, time))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.time").exists())
                .andDo(print());
    }
}