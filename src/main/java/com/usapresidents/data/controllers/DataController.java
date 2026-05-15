package com.usapresidents.data.controllers;

import com.usapresidents.data.dtos.PartyAnalysisResponseDTO;
import com.usapresidents.data.services.DataService;
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
@RequestMapping("/data")
@RequiredArgsConstructor
@Validated
public class DataController {
    private final DataService dataService;

    @GetMapping("/analysis/party")
    public ResponseEntity<PartyAnalysisResponseDTO> getPartyAnalysis(
            @RequestParam(name = "name") @NotBlank String party){
        PartyAnalysisResponseDTO partyAnalysisResponseDTO = dataService.getPartyAnalysis(party);
        return new ResponseEntity<>(partyAnalysisResponseDTO, HttpStatus.OK);
    }
}
