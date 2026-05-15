package com.usapresidents.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresMarriage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pres_name", length = 20, nullable = false)
    private String presName;

    @Column(name = "spouse_name", length = 20, nullable = false)
    private String spouseName;

    @Column(name = "pres_age", nullable = false)
    private Integer presAge;

    @Column(name = "spouse_age", nullable = false)
    private Integer spouseAge;

    @Column(name = "number_children", nullable = false)
    private Integer numberChildren;

    @Column(name = "marriage_year", nullable = false)
    private Integer marriageYear;
}
