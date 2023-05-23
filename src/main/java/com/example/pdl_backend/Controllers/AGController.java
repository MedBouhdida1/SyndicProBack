package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Models.AG;
import com.example.pdl_backend.Models.PV;
import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Repositories.AGRepository;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ag")
public class AGController {
    @Autowired
    private AGRepository agRepository;

    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;

    @GetMapping
    private List<AG> ListAG(){
        return agRepository.findAll();
    }

    @PostMapping(value = "{id}")
    private AG addAg(@RequestBody AG ag, @PathVariable Long id){
        PresidentSyndic presidentSyndic=presidentSyndicRepository.findById(id).orElse(null);
        ag.setPresidentSyndic(presidentSyndic);
        agRepository.save(ag);
        return ag;
    }

    @GetMapping(value = "{id}")
    private Optional<AG> getAgById(@PathVariable Long id){
        return agRepository.findById(id);
    }

    @DeleteMapping(value = "{id}")
    private void deleteAG(@PathVariable Long id){
        agRepository.deleteById(id);
    }


    @PutMapping(value = "{id}")
    private ResponseEntity<?> updateAG(@PathVariable Long id, @RequestBody AG ag){
        if(!agRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        ag.setId(id);
        agRepository.save(ag);
        return ResponseEntity.status(HttpStatus.OK).body(ag);
    }


}
