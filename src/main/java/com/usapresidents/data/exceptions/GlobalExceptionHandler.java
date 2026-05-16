package com.usapresidents.data.exceptions;

import com.usapresidents.data.dtos.ApiErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO> handlePresidentNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ){
        ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),  // code HTTP 404
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiErrorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleRequestParamValidationError(
            HandlerMethodValidationException ex,
            HttpServletRequest request
    ){
        String errorMessage = ex.getValueResults().isEmpty()
                ?"Validation error on URL parameters"
                :ex.getValueResults().get(0).getResolvableErrors().get(0).getDefaultMessage();


        ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), // code HTTP 400
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage,
                request.getRequestURI()
        );

        return new ResponseEntity<>(apiErrorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleRequestBodyValidationError(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), // code HTTP 400
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage,
                request.getRequestURI()
        );

        return new ResponseEntity<>(apiErrorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
