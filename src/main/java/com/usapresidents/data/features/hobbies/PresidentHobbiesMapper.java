package com.usapresidents.data.features.hobbies;

import com.usapresidents.data.core.domain.models.PresHobby;
import com.usapresidents.data.features.hobbies.dto.HobbyResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PresidentHobbiesMapper {
    public HobbyResponseDto toHobbyResponseDto(PresHobby presHobby){
        return new HobbyResponseDto(
                presHobby.getId(),
                presHobby.getHobby(),
                presHobby.getPresident().getId()
        );
    }
}
