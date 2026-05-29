package com.usapresidents.data.features.administrations.dto;

public record AdministrationResponseDto(
    Long id,
    String presName,
    String vicePresName,
    Integer inauguratedYear
) { }
