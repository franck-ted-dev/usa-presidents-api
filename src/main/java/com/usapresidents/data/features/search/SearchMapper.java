package com.usapresidents.data.features.search;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.features.search.dto.PresidentDto;
import org.springframework.stereotype.Component;

@Component
public class SearchMapper {
    public PresidentDto toPresidentDto(President president){
        return new PresidentDto(
                president.getPresName(),
                president.getBirthYear(),
                president.getServYear(),
                president.getDeathAge(),
                president.getParty(),
                president.getStateBorn()
        );
    }
}
