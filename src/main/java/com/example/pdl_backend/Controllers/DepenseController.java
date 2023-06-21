package com.example.pdl_backend.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdl_backend.Models.Depense;
import com.example.pdl_backend.Models.Syndic;
import com.example.pdl_backend.Repositories.DepenseRepository;
import com.example.pdl_backend.Repositories.SyndicRepository;

@RestController
@RequestMapping(value = "/depense")
@CrossOrigin("*")
public class DepenseController {
    @Autowired
    private DepenseRepository depenseRepository;

    @Autowired
    private SyndicRepository syndicRepository;

    @GetMapping
    private List<Depense> ListAG(){
        return depenseRepository.findAll();
    }

    @PostMapping(value = "{id}")
    private Depense addDepense(@RequestBody Depense depense, @PathVariable Long id){
        Syndic syndic=syndicRepository.findById(id).orElse(null);
        depense.setSyndic(syndic);
        depense.setDate(LocalDate.now());
        depenseRepository.save(depense);
        return depense;
    }

  

    @DeleteMapping(value = "{id}")
    private void deleteDepense(@PathVariable Long id){
        depenseRepository.deleteById(id);
    }

     @GetMapping(value = "{id}")
    private List<Depense> getDepenseBySyndic(@PathVariable Long id){
        Optional<Syndic> synd = syndicRepository.findById(id);
        return depenseRepository.findBySyndic(synd.get()) ;
    }
}
