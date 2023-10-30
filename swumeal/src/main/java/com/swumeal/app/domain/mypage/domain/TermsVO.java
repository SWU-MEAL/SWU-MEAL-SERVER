package com.swumeal.app.domain.mypage.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@Data
@NoArgsConstructor
public class TermsVO {
    private Long id;
    private Integer num;
    private String title;
    private String content;
    private LocalDateTime updated_at;
}
