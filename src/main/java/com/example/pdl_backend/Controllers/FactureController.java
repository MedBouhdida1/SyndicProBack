package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Models.AG;
import com.example.pdl_backend.Models.Facture;
import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Models.Resident;
import com.example.pdl_backend.Repositories.FactureRepository;
import com.example.pdl_backend.Repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
@CrossOrigin("*")
public class FactureController {


    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ResidentRepository residentRepository;


    @PostMapping(value = "{id}")
    private Facture addFacture(@RequestBody Facture facture, @PathVariable Long id){
        Resident resident=residentRepository.findById(id).orElse(null);
        facture.setResident(resident);
        factureRepository.save(facture);
        return facture;
    }


    @GetMapping
    private List<Facture> lstFactures(){
        return factureRepository.findAll();
    }

    @DeleteMapping(value = "{id}")
    private void deleteFacture(@PathVariable Long id){
        factureRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    private ResponseEntity<?> updateFacture(@PathVariable Long id, @RequestBody Facture facture){
        if(!factureRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        facture.setId(id);
        factureRepository.save(facture);
        return ResponseEntity.status(HttpStatus.OK).body(facture);
    }


}
