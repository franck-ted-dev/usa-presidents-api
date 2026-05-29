package com.usapresidents.data.features.hobbies.dto;

public record HobbyResponseDto(
        Long id,
        String hobby,
        Long presidentId
) { }

/*
 * Including both the hobby ID and the associated president ID
 * ensures compliance with modern REST API design principles.
 * This allows the front-end to efficiently handle list indexing (keys),
 * makes the application scalable for future mutations (update/delete),
 * and facilitates seamless resource navigation.
 */
