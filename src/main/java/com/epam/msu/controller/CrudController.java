package com.epam.msu.controller;

import com.epam.msu.entity.Certificate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CrudController {

    private static Certificate certificate = new Certificate();

    static{
        certificate.setId(11);
        certificate.setName("name");
        certificate.setDescription("description");
    }

    @GetMapping("/")
    public String  mainRest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(certificate);
    }

    @PutMapping("/add")
    public String addNewCertificate(@RequestParam String name,
                                    @RequestParam String description){
        return "main";
    }

}
