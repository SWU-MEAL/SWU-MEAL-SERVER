package com.swumeal.app.domain.mypage.api;

import com.swumeal.app.domain.mypage.dto.TermsAndPolicyDTO;
import com.swumeal.app.domain.mypage.service.MypageTermsAndPolicyService;
import com.swumeal.app.global.common.response.DataResponseDto;
import com.swumeal.app.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/mypage/*")
public class MypageController {
    private final MypageTermsAndPolicyService mypageTermsAndPolicyService;

    @GetMapping("terms")
    public ResponseEntity<ResponseDto> goToTerms() {
        TermsAndPolicyDTO termsAndPolicyDTO = mypageTermsAndPolicyService.getTermsAndPolicy();

        return ResponseEntity.ok(DataResponseDto.of(termsAndPolicyDTO, 200));
    }
}