package com.epam.msu.controller;

import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CrudController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CertificateServiceImpl certificateService;

    @Autowired
    public CrudController(CertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }


    @GetMapping
    public String mainRest(@RequestParam(required = false) String tag) throws JsonProcessingException {
        if (tag == null) {
            List<Certificate> certificates = certificateService.getAllCertificates();
            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
            System.out.println("WITHOUT TO STRING: " + instant);
            System.out.println("WITH TO STRING: " + instant.toString());
            return objectMapper.writeValueAsString(certificates);
        }
        return objectMapper.writeValueAsString(certificateService.getAllCertificatesByTagName(tag));
    }


    @GetMapping("{id}")
    public ResponseEntity getCertificateById(@PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        Certificate certificate = null;
        try{
            certificate = certificateService.getCertificateById(certificateId);
        }catch (CertificateNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }
        return new ResponseEntity<Certificate>(certificate, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewCertificate(@RequestBody Certificate certificate) throws JsonProcessingException {
        certificateService.createNewCertificate(certificate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public String updateCertificate(@RequestBody Certificate certificate, @PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        certificate.setId(certificateId);
        certificateService.updateCertificateById(certificate, certificateId);
        return objectMapper.writeValueAsString(certificate);
    }

    @DeleteMapping("{id}")
    public String deleteCertificate(@PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        Certificate certificate = certificateService.getCertificateById(certificateId);
        certificateService.deleteCertificateById(certificateId);
        return "DELETED: \n" + objectMapper.writeValueAsString(certificate);
    }

}
