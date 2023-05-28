package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Models.PresidentSyndic;
import com.example.pdl_backend.Models.Resident;
import com.example.pdl_backend.Models.Syndic;
import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
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

@RestController
@RequestMapping(value = "/presidentsyndic")

public class PresidentSyndicController {

    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    private PresidentSyndic addPresidentSyndic(@RequestBody PresidentSyndic presidentSyndic){
        presidentSyndic.setPassword(this.bCryptPasswordEncoder.encode(presidentSyndic.getPassword()));
        presidentSyndicRepository.save(presidentSyndic);
        return presidentSyndic;
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody PresidentSyndic presidentSyndic) {

        HashMap<String, Object> response = new HashMap<>();

        PresidentSyndic presidentSyndicFromDB = presidentSyndicRepository.findByEmail(presidentSyndic.getEmail());

        if (presidentSyndicFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(presidentSyndic.getPassword(), presidentSyndicFromDB.getPassword());

            if (!compare) {
                response.put("message", "Wrong password !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                String token = Jwts.builder()
                        .claim("data", presidentSyndicFromDB.getId())
                        .claim("role", "presidentsyndic")
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }
    @GetMapping
    private List<PresidentSyndic> listResidents(){
        return presidentSyndicRepository.findAll();
    }

}
