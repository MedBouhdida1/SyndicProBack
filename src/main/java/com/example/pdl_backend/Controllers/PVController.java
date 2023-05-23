package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Models.PV;
import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Repositories.PVRepository;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pv")

public class PVController {



    @Autowired
    private PVRepository pvRepository;

    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;

    @GetMapping
    private List<PV> ListPV(){
        return pvRepository.findAll();
    }

    @PostMapping(value = "{id}")
    private PV addPv(@RequestBody PV pv, @PathVariable Long id){
        PresidentSyndic presidentSyndic=presidentSyndicRepository.findById(id).orElse(null);
        pv.setPresidentSyndic(presidentSyndic);
        pvRepository.save(pv);
        return pv;
    }

    @GetMapping(value = "{id}")
    private Optional<PV> getPvById(@PathVariable Long id){
        return pvRepository.findById(id);
    }

    @DeleteMapping(value = "{id}")
    private void deletePV(@PathVariable Long id){
        pvRepository.deleteById(id);
    }


    @PutMapping(value = "{id}")
    private ResponseEntity<?> updatePV(@PathVariable Long id, @RequestBody PV pv){
        if(!pvRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        pv.setId(id);
        pvRepository.save(pv);
        return ResponseEntity.status(HttpStatus.OK).body(pv);
    }
}
