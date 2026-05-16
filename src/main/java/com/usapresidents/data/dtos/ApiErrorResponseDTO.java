package com.usapresidents.data.dtos;

import java.time.LocalDateTime;

public record ApiErrorResponseDTO(
        LocalDateTime timestamp,
        int httpStatus,
        String error,
        String message,
        String path
) {}
