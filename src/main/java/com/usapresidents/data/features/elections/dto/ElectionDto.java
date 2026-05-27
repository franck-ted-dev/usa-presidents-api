package com.usapresidents.data.features.elections.dto;

public record ElectionDto(
        Long id,
        Integer electionYear,
        String candidate,
        Integer votes,
        char winnerLoserIndic
) {}
