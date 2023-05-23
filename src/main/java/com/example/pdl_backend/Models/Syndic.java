package com.example.pdl_backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "syndic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Syndic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "president_syndic_id")
    @JsonIgnoreProperties("syndics")
    private PresidentSyndic presidentSyndic;


}