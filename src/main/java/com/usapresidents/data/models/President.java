package com.usapresidents.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class President {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pres_name", length = 20, nullable = false)
    private String presName;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @Column(name = "serv_year", nullable = false)
    private Integer servYear;

    @Column(name = "death_age", nullable = true)
    private Integer deathAge;

    @Column(name = "party", length = 15, nullable = false)
    private String party;

    @Column(name = "state_born", length = 15, nullable = false)
    private String stateBorn;
}
