package com.usapresidents.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "election_year", nullable = false)
    private Integer electionYear;

    @Column(name = "candidate", nullable = false)
    private String candidate;

    @Column(name = "votes", nullable = false)
    private Integer votes;

    @Column(name = "winner_loser_indic", nullable = false)
    private String winnerLoserIndic;
}
