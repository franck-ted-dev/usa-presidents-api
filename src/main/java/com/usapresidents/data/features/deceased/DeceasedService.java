package com.usapresidents.data.features.deceased;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.features.deceased.dto.PresidentDeathDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeceasedService {
    private final PresidentRepository presidentRepository;
    private final DeceasedMapper deceasedMapper;

    public List<PresidentDeathDto> getTopTenOldestDeceasedPresidents(){

        Sort sort = Sort.by("deathAge").descending();
        Pageable pageable = PageRequest.of(0,10, sort);

        List<President> presidents = presidentRepository.findByDeathAgeIsNotNull(pageable).getContent();
        return presidents.stream()
                .map(deceasedMapper::toPresidentDeathDto)
                .toList();
    }
}
