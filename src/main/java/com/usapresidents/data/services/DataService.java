package com.usapresidents.data.services;

import com.usapresidents.data.dtos.PartyAnalysisResponseDTO;
import com.usapresidents.data.exceptions.ResourceNotFoundException;
import com.usapresidents.data.mappers.DataMapper;
import com.usapresidents.data.models.Administration;
import com.usapresidents.data.models.Election;
import com.usapresidents.data.models.President;
import com.usapresidents.data.repositories.AdministrationRepository;
import com.usapresidents.data.repositories.ElectionRepository;
import com.usapresidents.data.repositories.PresidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {
    private final AdministrationRepository administrationRepository;
    private final PresidentRepository presidentRepository;
    private final ElectionRepository electionRepository;
    private final DataMapper dataMapper;

    public PartyAnalysisResponseDTO getPartyAnalysis(String party){

        // tous les presidents du parti "party" (objets complets)
        List<President> presidents = presidentRepository.findByParty(party);
        // On vérifie si le party politique existe en base de donnees
        if(presidents.isEmpty()){
            // si absent, on lève l'exception
            throw new ResourceNotFoundException("");
        }
        List<String> presNames = presidents.stream() // noms de ces presidents
                .map(President::getPresName)
                .toList();

        int totalPresident = presidents.size(); // element de PartyAnalysisResponseDTO

        // toutes les administrations de ces presidents
        List<Administration> administrations = administrationRepository.findByPresidentIn(presidents);
        // transformations de ces admins en adminDTO
        List<PartyAnalysisResponseDTO.AdministrationInfoDTO> administrationInfoDTOS = administrations.stream()
                .map(dataMapper::toAdministrationInfoDTO)
                .toList();

        char winnerLoserIndic = 'W';
        // toutes les elections gagnées par ces presidents
        List<Election> elections = electionRepository.
                findByCandidateInAndWinnerLoserIndic(presNames, winnerLoserIndic);
        // toutes ces elections transformées en electionDTO
        List<PartyAnalysisResponseDTO.WinElectionInfoDTO> winElectionInfoDTOS = elections.stream()
                .map(dataMapper::toWinElectionInfoDTO)
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
