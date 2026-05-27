package com.usapresidents.data.features.search;

public record PresidentSearchRequest(
        String party,
        String stateBorn,
        Integer birthYearMin,
        Integer birthYearMax
) { }
