package com.usapresidents.data.features.birthstate.dto;

public record PresidentDto(
        String presName,
        Integer birthYear,
        Integer servYear,
        Integer deathAge,
        String party
) { }
