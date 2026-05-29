package com.usapresidents.data.features.childrenranking.dto;

public record PresidentChildrenRankingDto(
        String presName,
        String party,
        String stateBorn,
        int totalChildren // Wird im Service berechnet
) { }
