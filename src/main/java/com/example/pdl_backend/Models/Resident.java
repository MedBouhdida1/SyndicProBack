package com.example.pdl_backend.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resident")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String password;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "president_syndic_id")
    @JsonIgnoreProperties({"residents", "ags","syndics"})

    private PresidentSyndic presidentSyndic;

    @ManyToOne
    @JoinColumn(name = "syndic_id")
    @JsonIgnoreProperties("residents")
    private Syndic syndic;

    @OneToMany(mappedBy = "resident",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Reclamation> depense = new ArrayList<>();


    @OneToMany(mappedBy = "resident",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Facture> factures = new ArrayList<>();


    @OneToMany(mappedBy = "resident",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Reclamation> reclamation = new ArrayList<>();

}