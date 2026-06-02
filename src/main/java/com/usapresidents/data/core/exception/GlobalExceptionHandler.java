package com.usapresidents.data.core.exception;

import com.usapresidents.data.core.dto.ApiErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handlePresidentNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ){
        ApiErrorResponseDto apiErrorResponseDTO = new ApiErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiErrorResponseDTO, HttpStatus.NOT_FOUND);
    }

    // Handles both types of parameter validation exceptions to return a clean 400 Bad Request
    @ExceptionHandler({
            org.springframework.web.method.annotation.HandlerMethodValidationException.class,
            jakarta.validation.ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ApiErrorResponseDto> handleValidationErrors(Exception ex, HttpServletRequest request) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed: One or more parameters are invalid.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleAllErrors(Exception ex, HttpServletRequest request) {
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred. Please try again later.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
