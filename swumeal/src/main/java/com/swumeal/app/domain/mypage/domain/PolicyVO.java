package com.swumeal.app.domain.mypage.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class PolicyVO {
    private Long id;
    private Integer num;
    private String title;
    private String content;
}
