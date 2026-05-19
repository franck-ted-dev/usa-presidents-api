package com.usapresidents.data.core.domain.models;

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
@Table(name = "PRES_MARRIAGE")
public class PresMarriage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // un president peut avoir plusieurs épouses
    @JoinColumn(name = "PRESIDENT_ID", nullable = false)
    private President president;

    @Column(name = "SPOUSE_NAME", length = 50, nullable = false)
    private String spouseName;

    @Column(name = "PRES_AGE", nullable = false)
    private Integer presAge;

    @Column(name = "SPOUSE_AGE", nullable = false)
    private Integer spouseAge;

    @Column(name = "NUMBER_CHILDREN", nullable = false)
    private Integer numberChildren;

    @Column(name = "MARRIAGE_YEAR", nullable = false)
    private Integer marriageYear;
}
