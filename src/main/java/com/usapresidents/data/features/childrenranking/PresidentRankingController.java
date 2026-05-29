package com.usapresidents.data.features.childrenranking;

import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.childrenranking.dto.PresidentChildrenRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/presidents")
@RequiredArgsConstructor
public class PresidentRankingController {
    private final PresidentRankingService presidentRankingService;

    @GetMapping("/ranking/children")
    public ResponseEntity<SlicedResponseDto<PresidentChildrenRankingDto>> getChildrenRanking(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        SlicedResponseDto<PresidentChildrenRankingDto> ranking =
                presidentRankingService.getPresidentsRankingByChildren(pageable);
        return ResponseEntity.ok(ranking);
    }
}
