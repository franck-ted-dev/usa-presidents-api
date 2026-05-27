package com.usapresidents.data.core.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PagedResponseDto<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int number,
        int size,
        int numberOfElements,
        boolean isFirst,
        boolean isLast
) {
    public PagedResponseDto(Page<T> page){
        this(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}
