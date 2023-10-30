package com.swumeal.app.domain.mypage.service;


import com.swumeal.app.domain.mypage.domain.MypageDAO;
import com.swumeal.app.domain.mypage.dto.PolicyDTO;
import com.swumeal.app.domain.mypage.dto.TermsAndPolicyDTO;
import com.swumeal.app.domain.mypage.dto.TermsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MypageTermsAndPolicyService {
    private final MypageDAO mypageDAO;

    @Transactional
    public TermsAndPolicyDTO getTermsAndPolicy() {
        TermsDTO termsDTO = new TermsDTO(mypageDAO.findAllTerms());
        PolicyDTO policyDTO = new PolicyDTO(mypageDAO.findAllPolicy());

        return new TermsAndPolicyDTO(termsDTO.toString(), policyDTO.toString());
    }
}
