package com.usapresidents.data.features.birthstate;

import com.usapresidents.data.core.domain.models.President;
import com.usapresidents.data.features.birthstate.dto.PresidentDto;
import org.springframework.stereotype.Component;

@Component
public class birthstateMapper {
    public PresidentDto toPresidentDto(President president){
        return new PresidentDto(
                president.getPresName(),
                president.getBirthYear(),
                president.getServYear(),
                president.getDeathAge(),
                president.getParty()
        );
    }
}
