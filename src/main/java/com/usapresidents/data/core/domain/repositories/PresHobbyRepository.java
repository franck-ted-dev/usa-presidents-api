package com.usapresidents.data.core.domain.repositories;

import com.usapresidents.data.core.domain.models.PresHobby;
import com.usapresidents.data.core.domain.models.President;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresHobbyRepository extends JpaRepository<PresHobby,Long> {
    Page<PresHobby> findByPresident(President president, Pageable pageable);
}
