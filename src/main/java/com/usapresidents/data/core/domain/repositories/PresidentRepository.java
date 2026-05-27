package com.usapresidents.data.core.domain.repositories;

import com.usapresidents.data.core.domain.models.President;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PresidentRepository extends JpaRepository<President,Long>, JpaSpecificationExecutor<President> {
    // on veut tous les presidents dont le party est celui-ci
    List<President> findByParty(String party);

    // on veut le president s'appelant presName
    President findByPresNameContainingIgnoreCase(String presName);

    // on veut tous les presidents qui sont nes dans tel etat
    Page<President> findByStateBorn(String stateBorn, Pageable pageable);

    boolean existsById(Long Id);

    Page<President> findByDeathAgeIsNotNull(Pageable pageable);
}
