package com.swumeal.app.domain.menu.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("MenuAPI test")
class MenuApiTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("findByDate API test")
    void findByDate() throws Exception {
        // given
        String query = "date";
        String date = "2023-10-04";
        String url = "/v1/menu";

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param(query, date))
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