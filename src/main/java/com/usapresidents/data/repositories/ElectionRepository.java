package com.usapresidents.data.repositories;

import com.usapresidents.data.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    // on veut récupérer toutes les elections, dont les candidats sont presents
    // dans la collection candidates, et
    // ces candidats ont le winnerLoserIndic donné.
    List<Election> findByCandidateInAndWinnerLoserIndic(Collection<String> candidates, char winnerLoserIndic);
}
