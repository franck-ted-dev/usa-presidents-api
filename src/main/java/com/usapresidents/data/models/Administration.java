package com.usapresidents.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "admin_number", nullable = false)
    private Integer adminNumber;

    @Column(name = "pres_name", nullable = false)
    private String presName;

    @Column(name = "year_inaugurated", nullable = false)
    private Integer yearInaugurated;
}
