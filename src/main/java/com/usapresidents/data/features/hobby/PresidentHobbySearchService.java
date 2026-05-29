package com.usapresidents.data.features.hobby;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.hobby.dto.PresidentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresidentHobbySearchService {

    private final PresidentRepository presidentRepository;
    private final PresidentHobbySearchMapper hobbyMapper;

    public SlicedResponseDto<PresidentDto> getPresidentWithHobby(String hobby, Pageable pageable){
        Slice<President> presidents = presidentRepository.findByHobby(hobby, pageable);

        Slice<PresidentDto> presidentDtos = presidents.map(hobbyMapper::toPresidentDto);

        return new SlicedResponseDto<>(presidentDtos);
    }
}
