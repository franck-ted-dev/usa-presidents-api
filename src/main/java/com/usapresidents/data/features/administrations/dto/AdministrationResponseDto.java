package com.usapresidents.data.features.administrations.dto;

/*
Pour chaque administration, afficher :
l'id du president,
son nom,
le nom de son vice president
et l'année de prise de fonction de cette administration
 */
public record AdministrationResponseDto(
    Long id,
    String presName,
    String vicePresName,
    Integer inauguratedYear
) { }
