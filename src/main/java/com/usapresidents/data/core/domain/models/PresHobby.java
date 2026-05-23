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
@Table(name = "PRES_HOBBY")
public class PresHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // A President can have many hobbies
    @JoinColumn(name = "PRESIDENT_ID", nullable = false)
    private President president;

    @Column(name = "HOBBY", length = 100, nullable = false)
    private String hobby;
}
