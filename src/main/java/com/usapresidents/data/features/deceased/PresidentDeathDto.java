package com.usapresidents.data.features.deceased;

public record PresidentDeathDto(
        Long id,
        String presName,
        Integer birthYear,
        Integer servYear,
        Integer deathAge,
        String party,
        String stateBorn
) { }
