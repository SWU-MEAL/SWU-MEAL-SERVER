package com.swumeal.app.global.mapper;

import com.swumeal.app.domain.mypage.dto.PolicyDTO;
import com.swumeal.app.domain.mypage.dto.TermsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Mypage Mapper Test")
@Transactional
class MypageMapperTest {
    @Autowired
    private MypageMapper mypageMapper;

    @Test
    @DisplayName("약관 조회 테스트")
    void selectAllTerms() {
        // given
        int termCount = 12;
        String titleStr = "서비스 제공";

        // when
        TermsDTO dto = new TermsDTO(mypageMapper.selectAllTerms());

        // then
        assertThat(dto.getTermsList().size()).as("전체 약관이 12 건이 아님.").isEqualTo(termCount);
        assertThat(dto.getTermsList().get(3).getTitle()).as("4번 약관 제목이 '서비스 제공'이 아님.").isEqualTo(titleStr);
    }

    @Test
    @DisplayName("개인정보 처리방침 조회 테스트")
    void selectAllPolicies() {
        // given
        int count = 14;
        String titleStr = "12. 개인정보에 관한 민원서비스";

        // when
        PolicyDTO dto = new PolicyDTO(mypageMapper.selectAllPolicies());

        // then
        assertThat(dto.getPolicyList().size()).as("전체 데이터 개수가 14 건이 아님.").isEqualTo(count);
        assertThat(dto.getPolicyList().get(12).getTitle()).as("12번 제목이 '12. 개인정보에 관한 민원서비스'이 아님.").isEqualTo(titleStr);
    }
}