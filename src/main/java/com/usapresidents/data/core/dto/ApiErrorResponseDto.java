package com.usapresidents.data.core.dto;

import java.time.LocalDateTime;

public record ApiErrorResponseDto(
        LocalDateTime timestamp,
        int httpStatus,
        String error,
        String message,
        String path
) {}
