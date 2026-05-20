package com.usapresidents.data.features.administrations;

import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.features.administrations.dto.AdministrationResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/presidents")
@RequiredArgsConstructor
@Validated
public class AdministrationController {
    private final AdministrationService administrationService;

    @GetMapping("/{id}/administrations")
    public ResponseEntity<PagedResponseDto<AdministrationResponseDto>> getAdministrations(
            @PathVariable @Positive(message = "L'id du president ne peut pas etre vide") long id,
            @PageableDefault(size = 10, sort = "hobby", direction = Sort.Direction.ASC) Pageable pageable
            ){
        PagedResponseDto<AdministrationResponseDto> administrations = administrationService.getAdministrations(id, pageable);
        return ResponseEntity.ok(administrations);
    }
}
