package com.epam.msu.controller;

import com.epam.msu.dao.impl.CertificateDaoImpl;
import com.epam.msu.entity.Certificate;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/certificates")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CrudController{

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CertificateServiceImpl certificateDao;

    @Autowired
    public CrudController(CertificateServiceImpl certificateDao) {
        this.certificateDao = certificateDao;
    }


    @GetMapping
    public String  mainRest() throws JsonProcessingException {
        List<Certificate> certificates = certificateDao.getAllCertificates();
        return objectMapper.writeValueAsString(certificates);
    }

    @GetMapping("{id}")
    public String getCertificateById(@PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        Certificate certificate = certificateDao.getCertificateById(certificateId);
        return objectMapper.writeValueAsString(certificate);
    }

    @PostMapping
    public String addNewCertificate(@RequestBody Certificate certificate) throws JsonProcessingException {
        System.out.println(certificate);
        certificateDao.createNewCertificate(certificate);
        return objectMapper.writeValueAsString(certificate);
    }

    @PatchMapping("{id}")
    public String updateCertificate(@RequestBody Certificate certificate, @PathVariable String id) throws JsonProcessingException {
        int certificateId = Integer.parseInt(id);
        certificate.setId(certificateId);
        certificateDao.updateCertificateById(certificate, certificateId);
        return objectMapper.writeValueAsString(certificate);
    }

    @DeleteMapping("{id}")
    public String deleteCertificate(@PathVariable String id) throws JsonProcessingException{
        return "main";
    }

}
