package com.usapresidents.data.features.birthstate;

import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.features.birthstate.dto.PresidentDto;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/presidents")
@RequiredArgsConstructor
@Validated
public class BirthstateController {
    private final BirthstateService birthstateService;

    @GetMapping("/stateborn")
    public ResponseEntity<PagedResponseDto<PresidentDto>> getPresidentsfromState(
            @RequestParam(name="name") @NotBlank(message = "State name cannot be blank") String stateBorn,
            @PageableDefault(sort = "presName", size = 5) Pageable pageable){
        PagedResponseDto<PresidentDto> responseDto = birthstateService.getPresidentsfromState(stateBorn, pageable);
        return ResponseEntity.ok(responseDto);
    }
}
