package com.usapresidents.data.features.administrations.dto;

/*
 * Transports a summary of a president's administration period,
 * including the associated president's metadata and the vice president.
 */
public record AdministrationResponseDto(
    Long id,
    String presName,
    String vicePresName,
    Integer inauguratedYear
) { }
