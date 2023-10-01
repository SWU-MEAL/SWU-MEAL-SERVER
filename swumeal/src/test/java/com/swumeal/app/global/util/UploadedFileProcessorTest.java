package com.swumeal.app.global.util;

import com.swumeal.app.domain.menu.vo.StaffMenuVo;
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

@Slf4j
@SpringBootTest
@DisplayName("ExcelUploadHandler 테스트")
class UploadedFileProcessorTest {
    @Autowired
    UploadedFileProcessor uploadedFileProcessor;

    @Test
    @DisplayName("엑셀 파일 업로드 테스트")
    void excelUploadTest() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("xlsx_file", "file.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("static/file.xlsx").getInputStream());

        // when
        LinkedList<StaffMenuVo> result = uploadedFileProcessor.readFile(file);

        // then
        assertThat(result.size()).as("[데이터 추출 실패] 추출 결과가 5건이 아님").isEqualTo(5);
        assertThat(result.get(0).getDate()).as("[데이터 추출 실패] 날짜 값 불일치").isEqualTo("2023-09-18");
        assertThat(result.get(0).getItems().get(0)).as("[데이터 추출 실패] 메뉴 아이템 값 불일치").isEqualTo("잡곡밥");

        for (StaffMenuVo dto : result) {
            log.info(dto.getDate().toString());
            for (String menu : dto.getItems())
                log.info(menu);
        }
    }
}