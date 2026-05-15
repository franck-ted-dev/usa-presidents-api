package com.usapresidents.data.mappers;

import com.usapresidents.data.dtos.PartyAnalysisResponseDTO;
import com.usapresidents.data.models.Administration;
import com.usapresidents.data.models.Election;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {
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
