package com.example.pdl_backend.Repositories;

import com.example.pdl_backend.Models.PresidentSyndic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresidentSyndicRepository extends JpaRepository<PresidentSyndic, Long> {



    PresidentSyndic findByEmail(String email);
}