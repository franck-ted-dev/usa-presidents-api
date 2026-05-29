package com.usapresidents.data.features.elections;

import com.usapresidents.data.core.domain.models.Election;
import com.usapresidents.data.core.domain.repositories.ElectionRepository;
import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.elections.dto.ElectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    public SlicedResponseDto<ElectionDto> getElections(){
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.ASC, "electionYear");

        Slice<Election> elections = electionRepository.findBy(pageable);
        Slice<ElectionDto> electionDtos = elections.map(electionMapper::toElectionDto);
        return new SlicedResponseDto<>(electionDtos);
    }
}
