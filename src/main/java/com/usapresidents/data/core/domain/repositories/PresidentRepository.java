package com.usapresidents.data.core.domain.repositories;

import com.usapresidents.data.core.domain.models.President;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresidentRepository extends JpaRepository<President,Long>, JpaSpecificationExecutor<President> {

    List<President> findByParty(String party);

    President findByPresNameIgnoreCase(String presName);

    Page<President> findByStateBorn(String stateBorn, Pageable pageable);

    boolean existsById(@NonNull Long id);

    Page<President> findByDeathAgeIsNotNull(Pageable pageable);

    // Custom JPQL query to fetch all presidents associated with a specific hobby
    @Query("SELECT p FROM PresHobby h JOIN h.president p WHERE h.hobby = :parameter")
    Slice<President> findByHobby(@Param ("parameter") String hobby, Pageable pageable);

    // Custom JPQL query that ranks presidents by children count without risking GROUP BY pagination bugs
    @Query("SELECT p " +
            "FROM PresMarriage m " +
            "JOIN m.president p " +
            "GROUP BY p " +
            "ORDER BY SUM(m.numberChildren) DESC")
    Slice<President> findPresidentsRankedByChildren(Pageable pageable);

    @Query("SELECT SUM(m.numberChildren) FROM PresMarriage m WHERE m.president = :president")
    Integer countChildrenByPresident(@Param("president") President president);
}
