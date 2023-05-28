package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Models.Resident;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import com.example.pdl_backend.Repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/resident")

public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;

    @PostMapping(value = "register/{id}")
    private ResponseEntity<?> addResident(@RequestBody Resident resident, @PathVariable Long id){
        if(residentRepository.existsByEmail(resident.getEmail())){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        resident.setPassword(this.bCryptPasswordEncoder.encode(resident.getPassword()));
        PresidentSyndic presidentSyndic=presidentSyndicRepository.findById(id).orElse(null);
        resident.setPresidentSyndic(presidentSyndic);
        residentRepository.save(resident);
        return ResponseEntity.status(HttpStatus.CREATED).body(resident);
    }

    @GetMapping
    private List<Resident> listResidents(){
        return residentRepository.findAll();
    }


    @GetMapping(value = "{id}")
    private Optional<Resident> getSyndicById(@PathVariable Long id){
        return residentRepository.findById(id);
    }



    @DeleteMapping(value = "{id}")
    private void deleteResident(@PathVariable Long id){
        residentRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    private ResponseEntity<?> updateResident(@PathVariable Long id, @RequestBody Resident resident){
        if(!residentRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        resident.setId(id);
        residentRepository.save(resident);
        return ResponseEntity.status(HttpStatus.OK).body(resident);
    }
}
