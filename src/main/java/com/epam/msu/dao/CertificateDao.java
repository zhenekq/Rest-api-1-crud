package com.epam.msu.dao;

import com.epam.msu.entity.Certificate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public interface CertificateDao {

    List<Certificate> getAllCertificates();
    Certificate getCertificateById(int id);
    void createNewCertificate(Certificate certificate);
    void updateCertificateById(Certificate certificate,int certificateId);
    void deleteCertificateById(int certificateId);
}
