package com.swumeal.app.global.util;

import com.swumeal.app.domain.menu.error.MenuErrorCode;
import com.swumeal.app.domain.menu.error.exception.DataUploadException;
import com.swumeal.app.domain.menu.vo.StaffMenuVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadedFileProcessor {
    public LinkedList<StaffMenuVo> readExcelFile(Workbook workbook) {
        final int START_ROW_NUM = 4;
        final int START_CELL_NUM = 2;
        final int REDUNDANT_ROW_COUNT = 3;

        Sheet worksheet = workbook.getSheetAt(0);
        int endRowNum = worksheet.getPhysicalNumberOfRows() - REDUNDANT_ROW_COUNT;
        LinkedList<StaffMenuVo> result = new LinkedList<>();

        // 날짜 기준 데이터 추출
        for (int l = START_CELL_NUM; l < START_CELL_NUM + 5; l++) {
            // 데이터 미존재 (공휴일 등)
            Cell cell = worksheet.getRow(START_ROW_NUM).getCell(l);
            if (cell == null || cell.toString().isEmpty())
                continue;

            // 날짜
            Date date = worksheet.getRow(START_ROW_NUM - 1).getCell(l).getDateCellValue();

            // 메뉴 아이템
            ArrayList<String> items = new ArrayList<>();
            for (int i = START_ROW_NUM; i < endRowNum; i++)
                items.add(worksheet.getRow(i).getCell(l).toString());

            result.add(new StaffMenuVo(date, items));
        }

        return result;
    }

    public LinkedList<StaffMenuVo> readFile(MultipartFile file) throws DataUploadException, IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (extension == null)
            throw new DataUploadException(MenuErrorCode.INVALID_FILE_EXTENSION);

        switch (extension) {
            case "xlsx" -> {
                return readExcelFile(new XSSFWorkbook(file.getInputStream()));
            }
            case "xls" -> {
                return readExcelFile(new HSSFWorkbook(file.getInputStream()));
            }
            default -> throw new DataUploadException(MenuErrorCode.INVALID_FILE_FORMAT);
        }
    }
}
