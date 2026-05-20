package com.usapresidents.data.core.domain.repositories;

import com.usapresidents.data.core.domain.models.Administration;
import com.usapresidents.data.core.domain.models.President;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AdministrationRepository extends JpaRepository<Administration,Long> {
    // on veut toutes les administrations qui ont pour presidents,
    // ceux se trouvant dans la collection presidents.
    List<Administration> findByPresidentIn(Collection<President> presidents);

    // On donne le Id du president et la DB
    // nous renvoie ses administrations
    Page<Administration> findByPresidentId(long presidentId, Pageable pageable);
}
