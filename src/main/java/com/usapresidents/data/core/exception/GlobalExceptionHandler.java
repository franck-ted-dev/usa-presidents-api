package com.usapresidents.data.core.exception;

import com.usapresidents.data.core.dto.ApiErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handlePresidentNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ){
        ApiErrorResponseDto apiErrorResponseDTO = new ApiErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),  // code HTTP 404
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiErrorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) // Fängt einfach ALLES ab
    public ResponseEntity<ApiErrorResponseDto> handleAllErrors(Exception ex, HttpServletRequest request) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now(),
                500,
                "Internal Server Error",
                "An unexpected error occurred. Please try again later.",
                request.getRequestURI()
        );
        return ResponseEntity.status(500).body(error);
    }
}
