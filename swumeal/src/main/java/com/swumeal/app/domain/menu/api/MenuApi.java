package com.swumeal.app.domain.menu.api;

import com.swumeal.app.domain.menu.dto.MenuListByDateDto;
import com.swumeal.app.domain.menu.dto.MenuListByTimeDto;
import com.swumeal.app.domain.menu.dto.request.DateRequestDto;
import com.swumeal.app.domain.menu.service.MenuReadService;
import com.swumeal.app.global.common.response.DataResponseDto;
import com.swumeal.app.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/menu")
public class MenuApi {
    private final MenuReadService menuReadService;

    @GetMapping
    public ResponseEntity<ResponseDto> findByDate(@RequestParam @Valid DateRequestDto date) {
        MenuListByDateDto menuListByDateDto = menuReadService.findByDate(date);

        return ResponseEntity.ok(DataResponseDto.of(menuListByDateDto, 200));
    }

    @GetMapping("/today")
    public ResponseEntity<ResponseDto> findByTime(@RequestParam @NotBlank String time) {
        MenuListByTimeDto menuListByTimeDto = menuReadService.findByTime(time);

        return ResponseEntity.ok(DataResponseDto.of(menuListByTimeDto, 200));
    }
}
