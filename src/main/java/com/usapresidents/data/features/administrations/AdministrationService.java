package com.usapresidents.data.features.administrations;

import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.core.domain.repositories.AdministrationRepository;
import com.usapresidents.data.core.domain.repositories.PresidentRepository;
import com.usapresidents.data.core.dto.PagedResponseDto;
import com.usapresidents.data.core.exception.ResourceNotFoundException;
import com.usapresidents.data.features.administrations.dto.AdministrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
/*
Je l'utilise pour générer automatiquement un constructeur
avec tous les champs marques comme final.
 */
@Service
/*
Pour que lors du component scan, Spring Boot identifie
cette classe comme un service.
 */
public class AdministrationService {

    private final AdministrationRepository administrationRepository;
    private final AdministrationMapper administrationMapper;
    private final PresidentRepository presidentRepository;

    // on utilise notre propre classe Page<T>
    public PagedResponseDto<AdministrationResponseDto> getAdministrations(Long presidentId, Pageable pageable){

        // On vérifie si un president correspond à cet Id
        if(!presidentRepository.existsById(presidentId)){
            throw new ResourceNotFoundException("Aucun president ne correspond a cet id");
        }

        // On récupère de la DB la page de toutes les administrations
        // du president dont l'Id correspond au paramètre
        Page<Administration> administrations = administrationRepository.findByPresidentId(presidentId, pageable);

        // On transforme la page d'administrations
        // en page d'AdministrationResponseDto
        Page<AdministrationResponseDto> administrationDtos = administrations.map(
              administrationMapper::toAdministrationResponseDto
        );

        // On retourne un objet de notre classe PagedResponseDto<AdministrationResponseDto>
        return new PagedResponseDto<>(administrationDtos);
    }
}
