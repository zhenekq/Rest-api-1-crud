package com.epam.msu.controller;

import com.epam.msu.entity.Certificate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/certificates")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CrudController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public String  mainRest() throws JsonProcessingException {
        List<Certificate> certificates = new ArrayList<>();
        Certificate firstCertificate = new Certificate();
        firstCertificate.setId(1);
        firstCertificate.setName("first certificate");
        firstCertificate.setDescription("first certificate description");
        Certificate secondCertificate = new Certificate();
        secondCertificate.setId(2);
        secondCertificate.setName("second certificate");
        secondCertificate.setDescription("Second description");
        certificates.add(firstCertificate);
        certificates.add(secondCertificate);
        return objectMapper.writeValueAsString(certificates);
    }

    @GetMapping("{id}")
    public String getCertificateById(@PathVariable String id) throws JsonProcessingException {
        return "mads";
    }

    @PostMapping()
    public String addNewCertificate() throws JsonProcessingException {
        return "mads";
    }

    @PutMapping("{id}")
    public String updateCertificate(@PathVariable String id) throws JsonProcessingException {
        return "mads";
    }

}
