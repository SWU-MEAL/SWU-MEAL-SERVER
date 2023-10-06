package com.swumeal.app.domain.menu.service;

import com.swumeal.app.dao.MenuDAO;
import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("엑셀 파일 업로드 Service 테스트")
@Slf4j
@Transactional
class FileUploadServiceTest {
    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    MenuDAO menuDAO;

    @Test
    @DisplayName("uploadData() 교직원 식당 테스트")
    void uploadData_staff() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("xlsx_file", "file.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("static/file.xlsx").getInputStream());
        String type = "교직원";

        // when
        fileUploadService.uploadData(file, type);

        // then
        MenuListByDateDto dto = menuDAO.findByDate("2023-09-18");
        assertThat(dto.getResult().get(0).getMenuList().get(0).getType()).as("식단 타입 오류").isEqualTo(type);
    }

    @Test
    @DisplayName("uploadData() 샬롬 식당 테스트")
    void uploadData_dorm() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("xlsx_file", "file2.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("static/file2.xlsx").getInputStream());
        String type = "샬롬";

        // when
        fileUploadService.uploadData(file, type);

        // then
        MenuListByDateDto dto = menuDAO.findByDate("2023-09-11");
        assertThat(dto.getResult().get(0).getMenuList().get(0).getType()).as("식단 타입 오류").isEqualTo(type);
    }
}