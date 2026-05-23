package com.usapresidents.data.features.birthstate;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.core.exception.ResourceNotFoundException;
import com.usapresidents.data.features.birthstate.dto.PresidentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class birthstateService {
    private final PresidentRepository presidentRepository;
    private final birthstateMapper birthstateMapper;

    public PagedResponseDto<PresidentDto> getPresidentsfromState(String state, Pageable pageable){
        Page<President> presidents = presidentRepository.findByStateBorn(state, pageable);

        // si la page est vide, le state correspondant n'a pas de president
        if(presidents.isEmpty()){
            throw new ResourceNotFoundException("Cet etat n'a pas donne naissance a un president");
        }

        Page<PresidentDto> presidentDtos = presidents.map(birthstateMapper::toPresidentDto);

        return new PagedResponseDto<>(presidentDtos);
    }
}
