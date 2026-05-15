package com.usapresidents.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminPrVp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_number", nullable = false)
    private Integer adminNumber;

    @Column(name = "pres_name", length = 20, nullable = false)
    private String presName;

    @Column(name = "vice_pres_name", length = 20, nullable = false)
    private String vicePresName;
}
