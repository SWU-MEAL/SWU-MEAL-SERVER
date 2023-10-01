package com.swumeal.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/mypage/*")
public class MypageController {
    @GetMapping("version")
    public void goToVersion(){;}

    @GetMapping("terms")
    public void goToTerms(){;}

}