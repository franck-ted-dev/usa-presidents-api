package com.usapresidents.data.features.hobbies.dto;

public record HobbyResponseDto(
        Long id,
        String hobby,
        Long presidentId
) { }


