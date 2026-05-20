package com.usapresidents.data.features.hobbies;

import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.features.hobbies.dto.HobbyResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hobbies")
@RequiredArgsConstructor
@Validated
public class HobbyController {
    private final HobbyService hobbyService;

    @GetMapping()
    public ResponseEntity<PagedResponseDto<HobbyResponseDto>> getHobbies(
            @RequestParam(name="name") @NotBlank(message = "Le nom du président ne peut pas être vide") String presName,
            @PageableDefault(size = 10, sort = "hobby", direction = Sort.Direction.ASC) Pageable pageable){
        PagedResponseDto<HobbyResponseDto> hobbies = hobbyService.getHobbies(presName, pageable);
        return new ResponseEntity<>(hobbies, HttpStatus.OK);
    }
}
