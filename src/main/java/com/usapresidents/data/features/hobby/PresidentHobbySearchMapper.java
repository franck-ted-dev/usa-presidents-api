package com.usapresidents.data.features.hobby;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.features.hobby.dto.PresidentDto;
import org.springframework.stereotype.Component;

@Component
public class PresidentHobbySearchMapper {
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
