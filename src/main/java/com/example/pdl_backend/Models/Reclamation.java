package com.example.pdl_backend.Models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reclamation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reclamation {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String objet;

    private String description;

    private String status;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "syndic_id")
    @JsonIgnore
    private Syndic syndic; 
    
    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonIgnore
    private Resident resident;
}
