package com.usapresidents.data.features.deceased;

import com.usapresidents.data.core.domain.models.President;
import org.springframework.stereotype.Component;

@Component
public class DeceasedMapper {
    public PresidentDeathDto toPresidentDeathDto(President president){
        return new PresidentDeathDto(
                president.getId(),
                president.getPresName(),
                president.getBirthYear(),
                president.getServYear(),
                president.getDeathAge(),
                president.getParty(),
                president.getStateBorn()
        );
    }
}
