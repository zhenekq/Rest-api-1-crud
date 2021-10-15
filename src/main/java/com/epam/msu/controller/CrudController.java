package com.epam.msu.controller;

import com.epam.msu.entity.Certificate;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Timestamp;
import java.time.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/certificates")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CrudController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CertificateServiceImpl certificateDao;

    @Autowired
    public CrudController(CertificateServiceImpl certificateDao) {
        this.certificateDao = certificateDao;
    }


    @GetMapping
    public String mainRest(@RequestParam(required = false) String tag) throws JsonProcessingException {
        if(tag == null) {
            List<Certificate> certificates = certificateDao.getAllCertificates();
            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
            System.out.println("WITHOUT TO STRING: " + instant);
            System.out.println("WITH TO STRING: " + instant.toString());
            return objectMapper.writeValueAsString(certificates);
        }
        return objectMapper.writeValueAsString(certificateDao.getAllCertificatesByTagName(tag));
    }


    @GetMapping("{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        Certificate certificate = certificateDao.getCertificateById(certificateId);
        if (certificate == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ResponseStatusException in testResponseStatusException");

        }
        return new ResponseEntity<Certificate>(certificate, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewCertificate(@RequestBody Certificate certificate) throws JsonProcessingException {
        certificateDao.createNewCertificate(certificate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public String updateCertificate(@RequestBody Certificate certificate, @PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        certificate.setId(certificateId);
        certificateDao.updateCertificateById(certificate, certificateId);
        return objectMapper.writeValueAsString(certificate);
    }

    @DeleteMapping("{id}")
    public String deleteCertificate(@PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        certificateDao.deleteCertificateById(certificateId);
        Certificate certificate = certificateDao.getCertificateById(certificateId);
        return "DELETED: \n" + objectMapper.writeValueAsString(certificate);
    }

}
