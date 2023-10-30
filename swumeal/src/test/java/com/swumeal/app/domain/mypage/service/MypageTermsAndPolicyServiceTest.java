package com.swumeal.app.domain.mypage.service;

import com.swumeal.app.domain.mypage.dto.TermsAndPolicyDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("MypageTermsAndPolicyService Test")
class MypageTermsAndPolicyServiceTest {
    @Autowired
    private MypageTermsAndPolicyService mypageTermsAndPolicyService;

    @Test
    @DisplayName("TermsAndPolicy Test")
    void getTermsAndPolicy() {
        // given
        String termStr = "\"회사\"는 즉시 회원탈퇴를 처리합니다.";
        String policyStr = "개인정보처리방침을 수정할 수 있습니다.";

        // when
        TermsAndPolicyDTO termsAndPolicyDTO = mypageTermsAndPolicyService.getTermsAndPolicy();

        // then
        assertThat(termsAndPolicyDTO.getTerms()).as("약관 데이터가 올바르지 않음").contains(termStr);
        assertThat(termsAndPolicyDTO.getPolicy()).as("개인정보 처리방침 데이터가 올바르지 않음").contains(policyStr);
    }
}