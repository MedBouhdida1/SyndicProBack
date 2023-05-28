package com.example.pdl_backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String objet;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String heure;

    private String duration;

    private String location;

    @ManyToOne
    @JoinColumn(name = "president_syndic_id")
    @JsonIgnoreProperties("ags")
    private PresidentSyndic presidentSyndic;


    @OneToOne(mappedBy = "ag",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ag")
    private PV pv;



}