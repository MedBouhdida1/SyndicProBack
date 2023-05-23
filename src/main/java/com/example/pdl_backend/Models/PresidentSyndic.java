package com.example.pdl_backend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PresidentSyndic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;


    @OneToMany(mappedBy = "presidentSyndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PV> PVs = new ArrayList<>();


    @OneToMany(mappedBy = "presidentSyndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AG> AGs = new ArrayList<>();


    @OneToMany(mappedBy = "presidentSyndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Syndic> syndics = new ArrayList<>();


}
