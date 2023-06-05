package com.example.pdl_backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "facture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private float amount;

    private String state;

    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    @Temporal(TemporalType.DATE)
    private LocalDate deadline;


    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonIgnoreProperties("factures")
    private Resident resident;

    @ManyToOne
    @JoinColumn(name = "syndic_id")
    @JsonIgnoreProperties("factures")
    private Syndic syndic;


}