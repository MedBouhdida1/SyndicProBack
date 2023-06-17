package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Models.Resident;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import com.example.pdl_backend.Repositories.ResidentRepository;
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
@RequestMapping(value = "/resident")
@CrossOrigin("*")
public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;

    @PostMapping(value = "register")
    private ResponseEntity<?> addResident(@RequestBody Resident resident){
        if(residentRepository.existsByEmail(resident.getEmail())){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        resident.setPassword(this.bCryptPasswordEncoder.encode(resident.getPassword()));
//        PresidentSyndic presidentSyndic=presidentSyndicRepository.findById(id).orElse(null);
//        resident.setPresidentSyndic(presidentSyndic);
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

    @PutMapping(value = "revoke/{id}")
    private ResponseEntity<?> revokeResident(@PathVariable Long id){
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if(residentOptional.isPresent()){
            Resident res = residentOptional.get();
            res.setSyndic(null);
            residentRepository.save(res);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }  
    }
    @GetMapping(value="getresidentsbysyndic/{id}")
    private List<Resident>Residents(@PathVariable Long id){
        return residentRepository.findBySyndicId(id);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Resident resident) {

        HashMap<String, Object> response = new HashMap<>();

        Resident residentFromDB = residentRepository.findByEmail(resident.getEmail());

        if (residentFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(resident.getPassword(), residentFromDB.getPassword());

            if (!compare) {
                response.put("message", "Wrong password !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                String token = Jwts.builder()
                        .claim("data", residentFromDB.getId())
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }
}
