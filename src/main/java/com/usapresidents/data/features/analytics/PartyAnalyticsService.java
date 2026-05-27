package com.usapresidents.data.features.analytics;

import com.usapresidents.data.features.analytics.dto.PartyAnalysisResponseDTO;
import com.usapresidents.data.core.exception.ResourceNotFoundException;
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

        // tous les presidents du parti "party" (objets complets)
        List<President> presidents = presidentRepository.findByParty(party);

        List<String> presNames = presidents.stream() // noms de ces presidents
                .map(President::getPresName)
                .toList();

        int totalPresident = presidents.size(); // element de PartyAnalysisResponseDTO

        // toutes les administrations de ces presidents
        List<Administration> administrations = administrationRepository.findByPresidentIn(presidents);
        // transformations de ces admins en adminDTO
        List<PartyAnalysisResponseDTO.AdministrationInfoDTO> administrationInfoDTOS = administrations.stream()
                .map(partyAnalyticsMapper::toAdministrationInfoDTO)
                .toList();

        char winnerLoserIndic = 'W';
        // toutes les elections gagnées par ces presidents
        List<Election> elections = electionRepository.
                findByCandidateInAndWinnerLoserIndic(presNames, winnerLoserIndic);
        // toutes ces elections transformées en electionDTO
        List<PartyAnalysisResponseDTO.WinElectionInfoDTO> winElectionInfoDTOS = elections.stream()
                .map(partyAnalyticsMapper::toWinElectionInfoDTO)
                .toList();

        // retourne l'objet responseDTO
        return new PartyAnalysisResponseDTO(
                party,
                totalPresident,
                administrationInfoDTOS,
                winElectionInfoDTOS
        );
    }
}
