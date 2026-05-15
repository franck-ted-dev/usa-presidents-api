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
@Table(name = "ELECTION")
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ELECTION_YEAR", nullable = false)
    private Integer electionYear;

    @Column(name = "CANDIDATE", nullable = false)
    private String candidate;

    @Column(name = "VOTES", nullable = false)
    private Integer votes;

    @Column(name = "WINNER_LOSER_INDIC", nullable = false, length = 1)
    private char winnerLoserIndic;
}
