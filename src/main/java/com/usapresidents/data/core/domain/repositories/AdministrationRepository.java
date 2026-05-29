package com.usapresidents.data.core.domain.repositories;

import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.core.domain.models.President;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AdministrationRepository extends JpaRepository<Administration,Long> {

    List<Administration> findByPresidentIn(Collection<President> presidents);

    Page<Administration> findByPresidentId(long presidentId, Pageable pageable);
}
