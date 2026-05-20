package com.usapresidents.data.features.administrations;

import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.features.administrations.dto.AdministrationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AdministrationMapper {
    public AdministrationResponseDto toAdministrationResponseDto(Administration administration){
        return new AdministrationResponseDto(
                administration.getId(),
                administration.getPresident().getPresName(),
                administration.getVicePresName(),
                administration.getYearInaugurated()
        );
    }
}
