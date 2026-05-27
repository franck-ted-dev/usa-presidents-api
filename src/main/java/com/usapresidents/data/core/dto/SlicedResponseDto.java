package com.usapresidents.data.core.dto;

import org.springframework.data.domain.Slice;
import java.util.List;

public record SlicedResponseDto<T>(
        List<T> content,
        int number,
        int size,
        int numberOfElements,
        boolean isFirst,
        boolean isLast,
        boolean hasNext
) {
    public SlicedResponseDto(Slice<T> slice) {
        this(
                slice.getContent(),
                slice.getNumber(),
                slice.getSize(),
                slice.getNumberOfElements(),
                slice.isFirst(),
                slice.isLast(),
                slice.hasNext()
        );
    }
}

