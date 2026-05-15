package com.usapresidents.data.repositories;

import com.usapresidents.data.models.Administration;
import com.usapresidents.data.models.President;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AdministrationRepository extends JpaRepository<Administration,Long> {
    // on veut toutes les administrations qui ont pour presidents,
    // ceux se trouvant dans la collection presidents.
    List<Administration> findByPresidentIn(Collection<President> presidents);
}
