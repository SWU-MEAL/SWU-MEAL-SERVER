package com.swumeal.app.domain.menu.service;

import com.swumeal.app.domain.menu.domain.MenuDAO;
import com.swumeal.app.domain.menu.domain.MenuItemVO;
import com.swumeal.app.domain.menu.domain.MenuVO;
import com.swumeal.app.domain.menu.error.MenuErrorCode;
import com.swumeal.app.domain.menu.error.exception.MenuApiException;
import com.swumeal.app.domain.menu.vo.MenuDataVo;
import com.swumeal.app.domain.model.MealTypeEnum;
import com.swumeal.app.global.util.UploadedFileProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadService {
    private final UploadedFileProcessor processor;
    private final MenuDAO menuDAO;

    @Transactional
    public LinkedList<MenuDataVo> uploadData(MultipartFile file, String type) throws IOException {
        MealTypeEnum typeEnum;
        switch (type) {
            case "교직원" -> typeEnum = MealTypeEnum.STAFF;
            case "샬롬" -> typeEnum = MealTypeEnum.DORMITORY;
            default -> throw new MenuApiException(MenuErrorCode.INVALID_TYPE);
        }

        LinkedList<MenuDataVo> result = processor.readFile(file, typeEnum);

        for (MenuDataVo vo : result) {
            MenuVO menuVO = new MenuVO(vo);
            menuDAO.save(menuVO);

            for (String item : vo.getItems())
                menuDAO.save(new MenuItemVO(menuVO, item));
        }

        return result;
    }
}
