package com.example.pdl_backend.Controllers;


import com.example.pdl_backend.Repositories.PresidentSyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/presidentsyndic")

public class PresidentSyndicController {

    @Autowired
    private PresidentSyndicRepository presidentSyndicRepository;


}
