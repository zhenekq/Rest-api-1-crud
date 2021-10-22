package com.epam.msu.controller;

import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Response;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.epam.msu.util.DatabaseProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Certificate>> mainRest(@RequestParam(required = false) String tag) throws JsonProcessingException {

        System.out.println(DatabaseProperties.dbPassword);
        System.out.println(DatabaseProperties.dbUsername);
        List<Certificate> certificates = null;
        if (tag == null) {
            certificates = certificateService.getAllCertificates();
            /*LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
            System.out.println("WITHOUT TO STRING: " + instant);
            System.out.println("WITH TO STRING: " + instant.toString());*/
            return new ResponseEntity<List<Certificate>>(certificates, HttpStatus.OK);
        }
        certificates = certificateService.getAllCertificatesByTagName(tag);
        return new ResponseEntity<List<Certificate>>(certificates, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificateById(@PathVariable int id) throws CertificateNotFoundException {
        Certificate certificate = certificateService.getCertificateById(id);
        if (certificate != null) {
            return new ResponseEntity<Certificate>(certificate, HttpStatus.OK);
        } else {
            throw new CertificateNotFoundException("Requested certificate not found (id = " + id + ")");
        }
    }

    @ExceptionHandler(CertificateNotFoundException.class)
    public Response handleException(CertificateNotFoundException e) {
        return new Response(e.getMessage());
    }


    @PostMapping
    public ResponseEntity<?> addNewCertificate(@RequestBody Certificate certificate) throws JsonProcessingException {
        certificateService.createNewCertificate(certificate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Certificate> updateCertificate(@RequestBody Certificate certificate, @PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        certificate.setId(certificateId);
        certificateService.updateCertificateById(certificate, certificateId);
        return new ResponseEntity<Certificate>(certificate, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Certificate> deleteCertificate(@PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        Certificate certificate = certificateService.getCertificateById(certificateId);
        certificateService.deleteCertificateById(certificateId);
        return ResponseEntity.status(200).body(certificate);
    }

}
