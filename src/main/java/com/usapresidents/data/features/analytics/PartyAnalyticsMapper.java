package com.usapresidents.data.features.analytics;

import com.usapresidents.data.features.analytics.dto.PartyAnalysisResponseDTO;
import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.core.domain.models.Election;
import org.springframework.stereotype.Component;

@Component
public class PartyAnalyticsMapper {
    public PartyAnalysisResponseDTO.AdministrationInfoDTO toAdministrationInfoDTO(Administration administration){
        return new PartyAnalysisResponseDTO.AdministrationInfoDTO(
                administration.getPresident().getPresName(),
                administration.getVicePresName(),
                administration.getYearInaugurated()
        );
    }

    public PartyAnalysisResponseDTO.WinElectionInfoDTO toWinElectionInfoDTO(Election election){
        return new PartyAnalysisResponseDTO.WinElectionInfoDTO(
                election.getElectionYear(),
                election.getVotes()
        );
    }
}
