package com.example.pdl_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdl_backend.Models.Depense;

import com.example.pdl_backend.Models.Syndic;


public interface DepenseRepository extends JpaRepository<Depense, Long>{
    List<Depense> findBySyndic(Syndic syndic);
}
