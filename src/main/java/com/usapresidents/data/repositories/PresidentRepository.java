package com.usapresidents.data.repositories;

import com.usapresidents.data.models.President;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresidentRepository extends JpaRepository<President,Long> {
    // on veut tous les presidents dont le party est celui-ci
    List<President> findByParty(String party);
}
