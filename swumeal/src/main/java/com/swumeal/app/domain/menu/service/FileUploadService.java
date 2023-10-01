package com.swumeal.app.domain.menu.service;

import com.swumeal.app.domain.menu.dao.MenuDAO;
import com.swumeal.app.domain.menu.vo.StaffMenuVo;
import com.swumeal.app.domain.vo.MenuItemVO;
import com.swumeal.app.domain.vo.MenuVO;
import com.swumeal.app.global.util.UploadedFileProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadService {
    private final UploadedFileProcessor processor;
    private final MenuDAO menuDAO;

    public LinkedList<StaffMenuVo> uploadData(MultipartFile file) throws IOException {
        LinkedList<StaffMenuVo> result = processor.readFile(file);

        for (StaffMenuVo vo : result) {
            MenuVO menuVO = new MenuVO(vo.getDate());
            menuDAO.save(menuVO);

            for (String item : vo.getItems())
                menuDAO.save(new MenuItemVO(menuVO, item));
        }

        return result;
    }
}
