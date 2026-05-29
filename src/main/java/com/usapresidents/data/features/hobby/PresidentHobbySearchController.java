package com.usapresidents.data.features.hobby;

import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.hobby.dto.PresidentDto;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
public class PresidentHobbySearchController {

    private final PresidentHobbySearchService hobbyService;

    @GetMapping("/search/hobby")
    public ResponseEntity<SlicedResponseDto<PresidentDto>> getPresidentWithHobby(
            @RequestParam(name = "hobby") @NotBlank(message = "Field hobby cannot be blank!") String hobby,
            Pageable pageable){
        SlicedResponseDto<PresidentDto> presidents = hobbyService.getPresidentWithHobby(hobby, pageable);

        return ResponseEntity.ok(presidents);
    }
}
