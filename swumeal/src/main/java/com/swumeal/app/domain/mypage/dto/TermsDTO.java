package com.swumeal.app.domain.mypage.dto;

import com.swumeal.app.domain.mypage.domain.TermsVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TermsDTO {
    private List<TermsVO> termsList;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (TermsVO vo : termsList) {
            stringBuilder.append(vo.getNum())
                    .append(". ")
                    .append(vo.getTitle())
                    .append("\n")
                    .append(vo.getContent())
                    .append("\n");
        }

        return stringBuilder.toString();
    }
}
