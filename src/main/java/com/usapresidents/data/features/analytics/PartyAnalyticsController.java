package com.usapresidents.data.features.analytics;

import com.usapresidents.data.features.analytics.dto.PartyAnalysisResponseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
@Validated
public class PartyAnalyticsController {
    private final PartyAnalyticsService partyAnalyticsService;

    @GetMapping("/party")
    public ResponseEntity<PartyAnalysisResponseDTO> getPartyAnalysis(
            @RequestParam(name = "name") @NotBlank(message = "Le nom du parti ne peut pas etre nul.") String party){
        PartyAnalysisResponseDTO partyAnalysisResponseDTO = partyAnalyticsService.getPartyAnalysis(party);
        return new ResponseEntity<>(partyAnalysisResponseDTO, HttpStatus.OK);
    }
}
