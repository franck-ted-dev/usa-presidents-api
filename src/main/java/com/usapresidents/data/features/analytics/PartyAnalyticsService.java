package com.usapresidents.data.features.analytics;

import com.usapresidents.data.core.exception.PartyNotFoundException;
import com.usapresidents.data.features.analytics.dto.PartyAnalysisResponseDTO;
import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.core.domain.models.Election;
import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.AdministrationRepository;
import com.usapresidents.data.core.domain.repositories.ElectionRepository;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyAnalyticsService {
    private final AdministrationRepository administrationRepository;
    private final PresidentRepository presidentRepository;
    private final ElectionRepository electionRepository;
    private final PartyAnalyticsMapper partyAnalyticsMapper;

    public PartyAnalysisResponseDTO getPartyAnalysis(String party){

        List<President> presidents = presidentRepository.findByParty(party);

        if (presidents.isEmpty()) {
            throw new PartyNotFoundException(party);
        }

        List<String> presNames = presidents.stream()
                .map(President::getPresName)
                .toList();

        int totalPresident = presidents.size();


        List<Administration> administrations = administrationRepository.findByPresidentIn(presidents);

        List<PartyAnalysisResponseDTO.AdministrationInfoDTO> administrationInfoDTOS = administrations.stream()
                .map(partyAnalyticsMapper::toAdministrationInfoDTO)
                .toList();

        Character winnerLoserIndic = 'W';

        List<Election> elections = electionRepository.
                findByCandidateInAndWinnerLoserIndic(presNames, winnerLoserIndic);

        List<PartyAnalysisResponseDTO.WinElectionInfoDTO> winElectionInfoDTOS = elections.stream()
                .map(partyAnalyticsMapper::toWinElectionInfoDTO)
                .toList();

        return new PartyAnalysisResponseDTO(
                party,
                totalPresident,
                administrationInfoDTOS,
                winElectionInfoDTOS
        );
    }
}
