package com.example.pdl_backend.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.pdl_backend.Models.Resident;
import com.example.pdl_backend.Repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdl_backend.Models.Reclamation;
import com.example.pdl_backend.Models.Syndic;

import com.example.pdl_backend.Repositories.ReclamationRepository;
import com.example.pdl_backend.Repositories.SyndicRepository;

@RestController
@RequestMapping(value = "/reclamation")
@CrossOrigin("*")
public class ReclamationController {
     @Autowired
    private ReclamationRepository reclamationRepository;


    @Autowired
    private SyndicRepository syndicRepository;
    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping
    private List<Reclamation> ListAG(){
        return reclamationRepository.findAll();
    }

    @PostMapping(value = "{id}")
    private Reclamation addReclamation(@RequestBody Reclamation reclamation, @PathVariable Long id){
        Resident res=residentRepository.findById(id).orElse(null);
        reclamation.setStatus("Pending");
        reclamation.setResident(res);
        reclamation.setSyndic(res.getSyndic());
        reclamation.setDate(LocalDate.now());
        reclamationRepository.save(reclamation);
        return reclamation;
    }

  

    @DeleteMapping(value = "{id}")
    private void deleteReclamation(@PathVariable Long id){
        reclamationRepository.deleteById(id);
    }

     @GetMapping(value = "{id}")
    private List<Reclamation> getReclamationBySyndic(@PathVariable Long id){
        Optional<Syndic> synd = syndicRepository.findById(id);
        return reclamationRepository.findBySyndic(synd.get()) ;
    }
}
