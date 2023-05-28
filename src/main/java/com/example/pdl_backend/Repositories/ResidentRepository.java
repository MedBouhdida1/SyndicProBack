package com.example.pdl_backend.Repositories;

import com.example.pdl_backend.Models.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {


    boolean existsByEmail(String email);
}