package com.example.pdl_backend.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PresidentSyndic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String password;

    private Long phone;

    @Temporal(TemporalType.DATE)
    private Date electionDate;




    @OneToMany(mappedBy = "presidentSyndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AG> ags = new ArrayList<>();


    @OneToMany(mappedBy = "presidentSyndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Syndic> syndics = new ArrayList<>();

    @OneToMany(mappedBy = "presidentSyndic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Resident> residents = new ArrayList<>();


}
