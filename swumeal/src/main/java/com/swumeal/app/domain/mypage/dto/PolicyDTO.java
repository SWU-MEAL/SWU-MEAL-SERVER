package com.swumeal.app.domain.mypage.dto;

import com.swumeal.app.domain.mypage.domain.PolicyVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PolicyDTO {
    private List<PolicyVO> policyList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (PolicyVO vo : policyList) {
            if (vo.getNum() == 0) {
                stringBuilder.append(vo.getContent())
                        .append("\n");
            } else {
                stringBuilder.append(vo.getTitle())
                        .append("\n")
                        .append(vo.getContent())
                        .append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
