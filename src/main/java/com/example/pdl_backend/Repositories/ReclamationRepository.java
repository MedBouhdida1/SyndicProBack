package com.example.pdl_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.pdl_backend.Models.Reclamation;
import com.example.pdl_backend.Models.Syndic;

public interface ReclamationRepository  extends JpaRepository<Reclamation, Long>{
        List<Reclamation> findBySyndic(Syndic syndic);

}
