package com.usapresidents.data.features.analytics.dto;

import java.util.List;

/*
 * Aggregates analytical data for a political party, including the distinct
 * count of presidents, a history of mandates (administrations), and all
 * victorious elections.
 */

public record PartyAnalysisResponseDTO(
        String party,
        int totalPresident,
        List<AdministrationInfoDTO> administrations,
        List<WinElectionInfoDTO> winElections
) {

    public record AdministrationInfoDTO(
            String presidentName,
            String vicePresidentName,
            int inauguratedYear
    ){}

    public record WinElectionInfoDTO(
            int electionYear,
            int votes
    ){}
}
