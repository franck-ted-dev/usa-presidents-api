package com.usapresidents.data.core.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ADMINISTRATION")
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // un president peut avoir plusieurs mandats = administrations
    @ManyToOne
    @JoinColumn(name = "PRESIDENT_ID", nullable = false)
    private President president;

    @Column(name = "VICE_PRES_NAME", length = 50, nullable = false)
    private String vicePresName;

    @Column(name = "YEAR_INAUGURATED", nullable = false)
    private Integer yearInaugurated;
}
