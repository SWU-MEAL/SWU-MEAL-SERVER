package com.swumeal.app.global.util;

import com.swumeal.app.domain.menu.error.MenuErrorCode;
import com.swumeal.app.domain.menu.error.exception.DataUploadException;
import com.swumeal.app.domain.menu.vo.MenuDataVo;
import com.swumeal.app.domain.model.CornerEnum;
import com.swumeal.app.domain.model.MealTypeEnum;
import com.swumeal.app.domain.model.TimeEnum;
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
    public boolean isBlankCell(Cell cell) {
        if (cell == null)
            return true;

        return cell.toString().isBlank();
    }

    public int getRowCount(TimeEnum time, CornerEnum corner) {
        if (time.equals(TimeEnum.BREAKFAST)) return 6;
        else if (time.equals(TimeEnum.DINNER)) return 7;
        else {
            if (corner == null)
                return 7;

            // 중식 -> 코너 기준 분류
            return corner.getRowCount();
        }
    }

    public LinkedList<MenuDataVo> getData(Date date, Sheet worksheet, MealTypeEnum type, TimeEnum time, int firstRow, int cellNum) {
        LinkedList<MenuDataVo> result = new LinkedList<>();
        final int MENU_COUNT = type.equals(MealTypeEnum.DORMITORY) && time.equals(TimeEnum.LUNCH) ? 3 : 1;
        int startRow = firstRow;

        for (int i = 0; i < MENU_COUNT; i++) {
            // 데이터 미존재 (공휴일 등)
            Cell cell = worksheet.getRow(startRow + 2).getCell(cellNum);
            if (isBlankCell(cell))
                continue;

            // Corner
            CornerEnum corner = null;
            if (MENU_COUNT == 3) {
                cell = worksheet.getRow(startRow++).getCell(cellNum);
                corner = cell.toString().contains("한식코너") ? CornerEnum.KOREAN : (cell.toString().contains("일품코너") ? CornerEnum.SPECIAL : CornerEnum.SNACK);
            }

            // 메뉴 아이템
            int rowCount = getRowCount(time, corner);
            ArrayList<String> items = new ArrayList<>();
            for (int l = startRow; l < startRow + rowCount; l++) {
                cell = worksheet.getRow(l).getCell(cellNum);
                if (!isBlankCell(cell))
                    items.add(cell.toString().trim());
            }

            result.add(MenuDataVo.builder()
                    .date(date)
                    .time(time)
                    .type(type)
                    .corner(corner)
                    .items(items)
                    .build());

            // 시작 행 업데이트
            startRow += rowCount;
        }

        return result;
    }

    public LinkedList<MenuDataVo> readExcel(Workbook workbook, MealTypeEnum type) {
        final int START_CELL_NUM = 2;
        final int START_ROW_NUM = type.equals(MealTypeEnum.DORMITORY) ? 2 : 3;
        Sheet worksheet = workbook.getSheetAt(0);
        LinkedList<MenuDataVo> result = new LinkedList<>();
        TimeEnum[] timeList = {TimeEnum.BREAKFAST, TimeEnum.LUNCH, TimeEnum.DINNER};

        for (int i = START_CELL_NUM; i < START_CELL_NUM + 5; i++) {
            Date date = worksheet.getRow(START_ROW_NUM).getCell(i).getDateCellValue();
            if (type.equals(MealTypeEnum.DORMITORY)) {
                for (TimeEnum time : timeList)
                    result.addAll(getData(date, worksheet, type, time, time.getDormStartRow(), i));

            } else {
                result.addAll(getData(date, worksheet, type, timeList[1], 4, i));
            }
        }

        return result;
    }

    public LinkedList<MenuDataVo> readFile(MultipartFile file, MealTypeEnum type) throws DataUploadException, IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (extension == null)
            throw new DataUploadException(MenuErrorCode.INVALID_FILE_EXTENSION);

        switch (extension) {
            case "xlsx" -> {
                return readExcel(new XSSFWorkbook(file.getInputStream()), type);
            }
            case "xls" -> {
                return readExcel(new HSSFWorkbook(file.getInputStream()), type);
            }
            default -> throw new DataUploadException(MenuErrorCode.INVALID_FILE_FORMAT);
        }
    }
}
