package com.usapresidents.data.features.elections;

import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.elections.dto.ElectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elections")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;

    @GetMapping("/all")
    public ResponseEntity<SlicedResponseDto<ElectionDto>> getElections(){
        SlicedResponseDto<ElectionDto> electionDtos = electionService.getElections();
        return ResponseEntity.ok(electionDtos);
    }
}
