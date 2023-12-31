package com.swumeal.app.domain.menu.api;

import com.swumeal.app.domain.menu.service.FileUploadService;
import com.swumeal.app.domain.menu.vo.MenuDataVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/upload")
public class MenuUploadApi {
    private final FileUploadService fileUploadService;

    @GetMapping("/xlsx")
    public String excelFileUpload() {
        return "dataForm";
    }

    @PostMapping("/result")
    public String fileUploadResult(@RequestParam("file") MultipartFile file, @RequestParam("type") String type, Model model) throws IOException {
        LinkedList<MenuDataVo> result = fileUploadService.uploadData(file, type);
        model.addAttribute("type", type);
        model.addAttribute("result", result);

        return "result";
    }
}
