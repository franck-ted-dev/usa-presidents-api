package com.usapresidents.data.features.search;

import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.search.dto.PresidentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/presidents")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<SlicedResponseDto<PresidentDto>> getPresidents(
            @ModelAttribute PresidentSearchRequest request
    ){
        SlicedResponseDto<PresidentDto> responseDto = searchService.searchPresidentDynamic(request);
        return ResponseEntity.ok(responseDto);
    }
}
