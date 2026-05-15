package com.usapresidents.data.dtos;

import java.util.List;

//Pour un parti donné, vous devez calculer et retourner un objet unique contenant :
// Le nom du parti analysé.
// Le nombre total de présidents différents ayant appartenu à ce parti
//     (attention aux doublons si un président a fait plusieurs mandats).
// Une liste de toutes les administrations (mandats) de ce parti, en affichant pour chacune :
//     le nom du président, le nom de son vice-président et l'année d'inauguration.
// Une liste de toutes les élections remportées par ce parti,
//     en affichant l'année de l'élection et le nombre de votes obtenus.

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
