package com.swumeal.app.domain.mypage.domain;

import com.swumeal.app.global.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageDAO {
    private final MypageMapper mypageMapper;

    // 약관 조회
    public List<TermsVO> findAllTerms() {
        return mypageMapper.selectAllTerms();
    }

    // 개인정보 처리 방침 조회
    public List<PolicyVO> findAllPolicy() {
        return mypageMapper.selectAllPolicies();
    }
}
