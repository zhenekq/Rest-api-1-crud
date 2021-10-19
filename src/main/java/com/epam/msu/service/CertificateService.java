package com.epam.msu.service;

import com.epam.msu.entity.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> getAllCertificates();
    Certificate getCertificateById(int id);
    void createNewCertificate(Certificate certificate);
    void updateCertificateById(Certificate certificate, int certificateId);
    void deleteCertificateById(int certificateId);
    List<Certificate> getAllCertificatesByTagName(String tagName);
}
