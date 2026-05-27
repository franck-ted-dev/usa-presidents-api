package com.usapresidents.data.features.deceased;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.PagedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeceasedService {
    private final PresidentRepository presidentRepository;
    private final DeceasedMapper deceasedMapper;

    public PagedResponseDto<PresidentDeathDto> getPresidentDeath(){
        // Wir erstellen unseres eigenes Objekt Sort und Pageable
        Sort kriterium = Sort.by("deathAge").descending();
        Pageable pageable = PageRequest.of(0,10, kriterium);

        // das Repository liefert uns alle verstorbenen Presidents
        // und dann umwandeln
        Page<President> presidents = presidentRepository.findByDeathAgeIsNotNull(pageable);
        Page<PresidentDeathDto> presidentDeathDtos = presidents.map(deceasedMapper::toPresidentDeathDto);
        return new PagedResponseDto<>(presidentDeathDtos);
    }
}
