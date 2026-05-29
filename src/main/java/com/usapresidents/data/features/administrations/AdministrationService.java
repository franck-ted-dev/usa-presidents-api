package com.usapresidents.data.features.administrations;

import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.core.domain.repositories.AdministrationRepository;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.core.exception.PresidentNotFoundException;
import com.usapresidents.data.features.administrations.dto.AdministrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdministrationService {

    private final AdministrationRepository administrationRepository;
    private final AdministrationMapper administrationMapper;
    private final PresidentRepository presidentRepository;

    public PagedResponseDto<AdministrationResponseDto> getAdministrations(Long presidentId, Pageable pageable){

        if (!presidentRepository.existsById(presidentId)) {
            throw new PresidentNotFoundException(presidentId);
        }

        Page<Administration> administrations = administrationRepository.findByPresidentId(presidentId, pageable);

        Page<AdministrationResponseDto> administrationDtos = administrations.map(
              administrationMapper::toAdministrationResponseDto
        );

        return new PagedResponseDto<>(administrationDtos);
    }
}
