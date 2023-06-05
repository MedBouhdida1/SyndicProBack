package com.example.pdl_backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

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

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String password;

    private Long phone;

    private float solde;

    @Temporal(TemporalType.DATE)
    private LocalDate electionDate;

    @ManyToOne
    @JoinColumn(name = "president_syndic_id")
    @JsonIgnoreProperties({"residents", "ags","syndics"})
    private PresidentSyndic presidentSyndic;


    @OneToMany(mappedBy = "syndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Resident> residents = new ArrayList<>();
    
    @OneToMany(mappedBy = "syndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();


}