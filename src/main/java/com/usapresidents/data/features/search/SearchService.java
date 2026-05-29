package com.usapresidents.data.features.search;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.search.dto.PresidentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final PresidentRepository presidentRepository;
    private final SearchMapper searchMapper;

    public SlicedResponseDto<PresidentDto> searchPresidentDynamic(
            PresidentSearchRequest request
    ){
        Sort sort = Sort.by("birthYear").descending()
                .and(Sort.by("deathAge").ascending());
        Pageable pageable = PageRequest.of(0, 3, sort);

        Specification<President> specification = (root, query, criteriaBuilder)
                -> criteriaBuilder.conjunction();

        if(request.party() != null && !request.party().isBlank()){
            specification = specification.and(PresidentSpecifications.hasParty(request.party()));
        }

        if(request.stateBorn() != null && !request.stateBorn().isBlank()){
            specification = specification.and(PresidentSpecifications.bornInState(request.stateBorn()));
        }

        if(request.birthYearMax() != null){
            specification = specification.and(PresidentSpecifications.birthYearLessThanOrEqual(request.birthYearMax()));
        }

        if(request.birthYearMin() != null){
            specification = specification.and(PresidentSpecifications.birthYearGreaterThanOrEqual(request.birthYearMin()));
        }

        Slice<President> presidents = presidentRepository.findAll(specification, pageable);

        Slice<PresidentDto> presidentDtos = presidents.map(searchMapper::toPresidentDto);

        return new SlicedResponseDto<>(presidentDtos);
    }
}
