package com.usapresidents.data.features.deceased;

import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.features.deceased.dto.PresidentDeathDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/presidents")
@RequiredArgsConstructor
public class DeceasedController {
    private final DeceasedService deceasedService;

    @GetMapping("/death")
    public ResponseEntity<PagedResponseDto<PresidentDeathDto>> getPresidentDeath(){

        PagedResponseDto<PresidentDeathDto> pagedResponseDto = deceasedService.getPresidentDeath();
        return ResponseEntity.ok(pagedResponseDto);
    }
}
