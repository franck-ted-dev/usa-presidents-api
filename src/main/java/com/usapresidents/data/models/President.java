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
@Table(name = "PRESIDENT")
public class President {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRES_NAME", length = 50, nullable = false, unique = true)
    private String presName;

    @Column(name = "BIRTH_YEAR", nullable = false)
    private Integer birthYear;

    @Column(name = "SERV_YEAR", nullable = false)
    private Integer servYear;

    @Column(name = "DEATH_AGE", nullable = true)
    private Integer deathAge;

    @Column(name = "PARTY", length = 30, nullable = false)
    private String party;

    @Column(name = "STATE_BORN", length = 30, nullable = false)
    private String stateBorn;
}
