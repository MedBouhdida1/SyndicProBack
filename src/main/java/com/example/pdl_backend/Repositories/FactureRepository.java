package com.example.pdl_backend.Repositories;

import com.example.pdl_backend.Models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}