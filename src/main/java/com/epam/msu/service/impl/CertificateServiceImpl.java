package com.epam.msu.service.impl;

import com.epam.msu.dao.impl.CertificateDaoImpl;
import com.epam.msu.entity.Certificate;
import com.epam.msu.service.CertificateService;
import com.epam.msu.service.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDaoImpl certificateDao;

    @Autowired
    public CertificateServiceImpl(CertificateDaoImpl certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateDao.getAllCertificates();
    }

    @Override
    public Certificate getCertificateById(int id) {
        if(Validation.isValidId(id)){
            return certificateDao.getCertificateById(id);
        }
        return null;
    }

    @Override
    public void createNewCertificate(Certificate certificate) {
        certificateDao.createNewCertificate(certificate);
    }

    @Override
    public void updateCertificateById(Certificate certificate, int certificateId) {
        certificateDao.updateCertificateById(certificate, certificateId);
    }

    @Override
    public void deleteCertificateById(int certificateId) {

    }
}
