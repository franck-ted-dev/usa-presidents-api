package com.usapresidents.data.features.childrenranking;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.SlicedResponseDto;
import com.usapresidents.data.features.childrenranking.dto.PresidentChildrenRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresidentRankingService {
    private final PresidentRepository presidentRepository;

    public SlicedResponseDto<PresidentChildrenRankingDto> getPresidentsRankingByChildren(Pageable pageable) {

        Slice<President> presidentSlice = presidentRepository.findPresidentsRankedByChildren(pageable);

        Slice<PresidentChildrenRankingDto> rankingSlice = presidentSlice.map(president -> {
            Integer totalChildren = presidentRepository.countChildrenByPresident(president);

            int childrenCount = (totalChildren != null) ? totalChildren : 0;

            return new PresidentChildrenRankingDto(
                    president.getPresName(),
                    president.getParty(),
                    president.getStateBorn(),
                    childrenCount
            );
        });

        return new SlicedResponseDto<>(rankingSlice);
    }
}
