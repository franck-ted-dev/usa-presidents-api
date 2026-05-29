package com.usapresidents.data.core.domain.repositories;

import com.usapresidents.data.core.domain.models.Election;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election, Long> {

    List<Election> findByCandidateInAndWinnerLoserIndic(Collection<String> candidates, Character winnerLoserIndic);

    Slice<Election> findBy(Pageable pageable);
}
