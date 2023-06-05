package com.example.pdl_backend.Controllers;



import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Models.Resident;
import com.example.pdl_backend.Models.Syndic;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import com.example.pdl_backend.Repositories.ResidentRepository;
import com.example.pdl_backend.Repositories.SyndicRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/syndic")
@CrossOrigin(origins = {"http://localhost:3000"})
public class SyndicController {

    @Autowired
    private SyndicRepository syndicRepository;
    @Autowired
    private ResidentRepository residentRepository;
    
    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @PostMapping(value = "register/{id}")
    private ResponseEntity<?>  addSyndic(@RequestBody Syndic syndic, @PathVariable Long id){
        if(syndicRepository.existsByEmail(syndic.getEmail())){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        syndic.setPassword(this.bCryptPasswordEncoder.encode(syndic.getPassword()));
        PresidentSyndic presidentSyndic=presidentSyndicRepository.findById(id).orElse(null);
        syndic.setPresidentSyndic(presidentSyndic);
        syndicRepository.save(syndic);
        return ResponseEntity.status(HttpStatus.CREATED).body(syndic);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Syndic syndic) {

        HashMap<String, Object> response = new HashMap<>();

        Syndic syndicFromDB = syndicRepository.findByEmail(syndic.getEmail());

        if (syndicFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else {

            Boolean compare = this.bCryptPasswordEncoder.matches(syndic.getPassword(), syndicFromDB.getPassword());

            if (!compare) {
                response.put("message", "Wrong password !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            else {
                String token = Jwts.builder()
                        .claim("data", syndicFromDB.getId())
                        .claim("role","syndic")
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

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
    private void deleteSyndic(@PathVariable Long id){
        syndicRepository.deleteById(id);
    }


    

    @PutMapping(value = "{id}")
    private ResponseEntity<?> updateSyndic(@PathVariable Long id, @RequestBody Syndic syndic){
        if(!syndicRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        syndic.setId(id);
  
        syndicRepository.save(syndic);
        return ResponseEntity.status(HttpStatus.OK).body(syndic);
    }
    @PutMapping(value = "residents/{id}")
    private ResponseEntity<?> assignResident(@PathVariable Long id, @RequestBody List<Long> residents){
       
        Optional<Syndic> syndicOptional = syndicRepository.findById(id);
        if(syndicOptional.isPresent()){
            Syndic syndic = syndicOptional.get();
            List<Resident> residentToAssign = residentRepository.findAllById(residents);
            for (Resident resident : residentToAssign) {
                resident.setSyndic(syndic);
            }
            
            residentRepository.saveAll(residentToAssign);
            return ResponseEntity.status(HttpStatus.OK).body(syndic);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
       
    }

}
