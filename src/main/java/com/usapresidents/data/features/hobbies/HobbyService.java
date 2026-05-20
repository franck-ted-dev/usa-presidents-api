package com.usapresidents.data.features.hobbies;

import com.usapresidents.data.core.domain.models.PresHobby;
import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresHobbyRepository;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.core.exception.ResourceNotFoundException;
import com.usapresidents.data.features.hobbies.dto.HobbyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HobbyService {
    private final PresHobbyRepository presHobbyRepository;
    private final PresidentRepository presidentRepository;
    private final HobbyMapper hobbyMapper;

    public PagedResponseDto<HobbyResponseDto> getHobbies(String presName, Pageable pageable){
        President president = presidentRepository.findByPresNameContainingIgnoreCase(presName);
        if(president == null){
            throw new ResourceNotFoundException(presName + " is not a name of President.");
        }

        // Page<Hobby> de ce president
        Page<PresHobby> hobbies = presHobbyRepository.findByPresident(president, pageable);
        Page<HobbyResponseDto> hobbiesDto = hobbies.map(hobbyMapper::toHobbyResponseDto);
        return new PagedResponseDto<>(hobbiesDto);
    }
}
