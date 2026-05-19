package com.usapresidents.data.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

// Je crée ma propre classe PagedResponseDto car la classe Page
// de base contient énormément de métadonnées qui ne servent
// à rien pour le frontend.
@Getter
@Setter
public class PagedResponseDto<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

    /*
    Je n'ai pas utilisé @AllArgsConstructor ici, car le but de ce DTO est
    d'encapsuler la transformation de l'objet Page de Spring Data.
    En écrivant ce constructeur personnalisé, j'évite de polluer
    mes services avec l'extraction manuelle de chaque métadonnée (taille, page courante, total).
    Le service lui passe simplement l'objet Page,
    et le DTO s'occupe du reste.
    */
    public PagedResponseDto(Page<T> page){
        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.isLast = page.isLast();
    }
}
