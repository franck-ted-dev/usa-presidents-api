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
public class birthstateController {
    private final birthstateService birthstateService;

    @GetMapping("/birthstate")
    public ResponseEntity<PagedResponseDto<PresidentDto>> getPresidentsfromState(
            @RequestParam(name="name") @NotBlank(message = "le nom de l'etat ne peut pas etre vide") String state,
            @PageableDefault(page = 0, sort = "presName", size = 5) Pageable pageable){
        PagedResponseDto<PresidentDto> responseDto = birthstateService.getPresidentsfromState(state, pageable);
        return ResponseEntity.ok(responseDto);
    }
}
