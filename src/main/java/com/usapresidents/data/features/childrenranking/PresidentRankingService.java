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
        // 1. Holt die nach Kindern sortierten Präsidenten als stabiles Slice
        Slice<President> presidentSlice = presidentRepository.findPresidentsRankedByChildren(pageable);

        // 2. Errechnet für jeden Präsidenten die Summe und mappt sie ins Record-DTO
        Slice<PresidentChildrenRankingDto> rankingSlice = presidentSlice.map(president -> {
            Integer totalChildren = presidentRepository.countChildrenByPresident(president);

            // Falls ein Präsident im System erfasst ist, aber totalChildren null liefert, setzen wir 0
            int childrenCount = (totalChildren != null) ? totalChildren : 0;

            return new PresidentChildrenRankingDto(
                    president.getPresName(),
                    president.getParty(),
                    president.getStateBorn(),
                    childrenCount
            );
        });

        // 3. Gibt das fertige SlicedResponseDto zurück
        return new SlicedResponseDto<>(rankingSlice);
    }
}
