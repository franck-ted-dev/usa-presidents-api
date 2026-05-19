package com.usapresidents.data.features.hobbies;

public record hobbyResponseDTO(
        Long id,
        String hobby,
        Long presidentId
) { }

/*
Même si l'affichage actuel ne demande que le nom du hobby,
inclure l'ID du hobby et l'ID du président est indispensable
pour respecter le contrat d'interface d'une API REST moderne.
Cela permet au front-end de gérer efficacement l'affichage des
listes (les clés d'index), de rendre l'application évolutive
pour de futures actions de modification/suppression,
et de faciliter la navigation entre les ressources liées.
*/
