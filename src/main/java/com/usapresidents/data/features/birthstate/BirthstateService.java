package com.usapresidents.data.features.birthstate;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.core.exception.StateNotFoundException;
import com.usapresidents.data.features.birthstate.dto.PresidentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BirthstateService {
    private final PresidentRepository presidentRepository;
    private final BirthstateMapper birthstateMapper;

    public PagedResponseDto<PresidentDto> getPresidentsfromState(String stateBorn, Pageable pageable){
        Page<President> presidents = presidentRepository.findByStateBorn(stateBorn, pageable);

        if(presidents.isEmpty()){
            throw new StateNotFoundException(stateBorn);
        }

        Page<PresidentDto> presidentDtos = presidents.map(birthstateMapper::toPresidentDto);

        return new PagedResponseDto<>(presidentDtos);
    }
}
