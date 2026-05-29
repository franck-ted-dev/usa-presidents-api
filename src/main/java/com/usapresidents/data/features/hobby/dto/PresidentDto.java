package com.usapresidents.data.features.hobby.dto;

public record PresidentDto(
        String presName,
        Integer birthYear,
        Integer servYear,
        Integer deathAge,
        String party,
        String stateBorn
) { }
