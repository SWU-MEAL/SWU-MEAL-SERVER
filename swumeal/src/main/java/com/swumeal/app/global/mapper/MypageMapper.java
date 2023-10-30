package com.swumeal.app.global.mapper;

import com.swumeal.app.domain.mypage.domain.PolicyVO;
import com.swumeal.app.domain.mypage.domain.TermsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {
    // Terms 조회
    List<TermsVO> selectAllTerms();

    // Privacy-policy 조회
    List<PolicyVO> selectAllPolicies();
}
