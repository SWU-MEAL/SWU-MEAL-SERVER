package com.swumeal.app.global.util;

import com.swumeal.app.domain.menu.vo.MenuDataVo;
import com.swumeal.app.domain.model.CornerEnum;
import com.swumeal.app.domain.model.MealTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("ExcelUploadHandler 테스트")
@Slf4j
class UploadedFileProcessorTest {
    @Autowired
    UploadedFileProcessor uploadedFileProcessor;

    @Test
    @DisplayName("교직원 엑셀 파일 업로드 테스트")
    void excelUploadTest_staff() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("xlsx_file", "file.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("static/file.xlsx").getInputStream());
        MealTypeEnum type = MealTypeEnum.STAFF;

        // when
        LinkedList<MenuDataVo> result = uploadedFileProcessor.readFile(file, type);

        // then
        for (MenuDataVo dto : result) {
            log.info(dto.getDate().toString());
            for (String menu : dto.getItems())
                log.info(menu);
        }

        assertThat(result.size()).as("[데이터 추출 실패] 추출 결과가 5건이 아님").isEqualTo(5);
        assertThat(result.get(0).getDate()).as("[데이터 추출 실패] 날짜 값 불일치").isEqualTo("2023-09-18");
        assertThat(result.get(0).getItems().get(0)).as("[데이터 추출 실패] 메뉴 아이템 값 불일치").isEqualTo("잡곡밥");
    }

    @Test
    @DisplayName("샬롬 엑셀 파일 업로드 테스트")
    void excelUploadTest_dorm() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("xlsx_file", "file2.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("static/file2.xlsx").getInputStream());
        MealTypeEnum type = MealTypeEnum.DORMITORY;

        // when
        LinkedList<MenuDataVo> result = uploadedFileProcessor.readFile(file, type);

        // then
        LinkedList<Integer> resultCount = new LinkedList<>();
        for (int i = 0; i < 4; i++)
            resultCount.add(0);

        for (MenuDataVo dto : result) {
            log.info(dto.getDate().toString());
            for (String menu : dto.getItems())
                log.info(menu);

            switch (dto.getDate().toString()) {
                case "2023-10-10" -> resultCount.set(0, resultCount.get(0) + 1);
                case "2023-10-11" -> resultCount.set(1, resultCount.get(1) + 1);
                case "2023-10-12" -> resultCount.set(2, resultCount.get(2) + 1);
                default -> resultCount.set(3, resultCount.get(3) + 1);
            }
        }

        for (int i = 0; i < 4; i++) {
            assertThat(resultCount.get(i)).as("[데이터 추출 실패] #" + i + " 추출 결과가 5건이 아님").isEqualTo(5);
        }

        assertThat(result.size()).as("[데이터 추출 실패] 추출 결과가 20건이 아님").isEqualTo(20);
        assertThat(result.get(0).getDate()).as("[데이터 추출 실패] 날짜 값 불일치").isEqualTo("2023-10-10");
        assertThat(result.get(0).getItems().get(0)).as("[데이터 추출 실패] 메뉴 아이템 값 불일치").isEqualTo("참치야채비빔밥");
        assertThat(result.get(2).getCorner()).as("[데이터 추출 실패] 메뉴 코너 값 불일치").isEqualTo(CornerEnum.SPECIAL);
    }

    @Test
    @DisplayName("샬롬 엑셀 파일 ver2. 업로드 테스트")
    void excelUploadTest_dorm2() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("xlsx_file", "file3.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("static/file3.xlsx").getInputStream());
        MealTypeEnum type = MealTypeEnum.DORMITORY;

        // when
        LinkedList<MenuDataVo> result = uploadedFileProcessor.readFile(file, type);

        // then
        LinkedList<Integer> resultCount = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            resultCount.add(0);

        for (MenuDataVo dto : result) {
            log.info(dto.getDate().toString());
            for (String menu : dto.getItems())
                log.info(menu);

            switch (dto.getDate().toString()) {
                case "2023-10-23" -> resultCount.set(0, resultCount.get(0) + 1);
                case "2023-10-24" -> resultCount.set(1, resultCount.get(1) + 1);
                case "2023-10-25" -> resultCount.set(2, resultCount.get(2) + 1);
                case "2023-10-26" -> resultCount.set(3, resultCount.get(3) + 1);
                default -> resultCount.set(4, resultCount.get(4) + 1);
            }
        }

        for (int i = 0; i < 4; i++) {
            assertThat(resultCount.get(i)).as("[데이터 추출 실패] #" + i + " 추출 결과가 5건이 아님").isEqualTo(5);
        }

        assertThat(result.size()).as("[데이터 추출 실패] 추출 결과가 25건이 아님").isEqualTo(25);
        assertThat(result.get(0).getDate()).as("[데이터 추출 실패] 날짜 값 불일치").isEqualTo("2023-10-23");
        assertThat(result.get(0).getItems().get(0)).as("[데이터 추출 실패] 메뉴 아이템 값 불일치").isEqualTo("야채죽");
        assertThat(result.get(2).getCorner()).as("[데이터 추출 실패] 메뉴 코너 값 불일치").isEqualTo(CornerEnum.SPECIAL);
    }
}