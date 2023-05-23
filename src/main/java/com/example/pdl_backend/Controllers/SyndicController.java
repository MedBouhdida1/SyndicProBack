package com.example.pdl_backend.Controllers;



import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Models.Syndic;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import com.example.pdl_backend.Repositories.SyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/syndic")
public class SyndicController {

    @Autowired
    private SyndicRepository syndicRepository;

    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;


    @PostMapping(value = "{id}")
    private Syndic addSyndic(@RequestBody Syndic syndic, @PathVariable Long id){
        PresidentSyndic presidentSyndic=presidentSyndicRepository.findById(id).orElse(null);
        syndic.setPresidentSyndic(presidentSyndic);
        syndicRepository.save(syndic);
        return syndic;
    }

    @GetMapping
    private List<Syndic> listSyndic(){
        return syndicRepository.findAll();
    }

    @GetMapping(value = "{id}")
    private Optional<Syndic> getSyndicById(@PathVariable Long id){
        return syndicRepository.findById(id);
    }


    @DeleteMapping(value = "{id}")
    private void deletePV(@PathVariable Long id){
        syndicRepository.deleteById(id);
    }


    @PutMapping(value = "{id}")
    private ResponseEntity<?> updatePV(@PathVariable Long id, @RequestBody Syndic syndic){
        if(!syndicRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        syndic.setId(id);
        syndicRepository.save(syndic);
        return ResponseEntity.status(HttpStatus.OK).body(syndic);
    }

}
