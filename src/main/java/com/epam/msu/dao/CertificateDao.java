package com.epam.msu.dao;

import com.epam.msu.entity.Certificate;
import java.util.List;

public interface CertificateDao {

    List<Certificate> getAllCertificates();
    Certificate getCertificateById(int id);
    Certificate createNewCertificate(Certificate certificate);
    void updateCertificateById(Certificate certificate,int certificateId);
    void deleteCertificateById(int certificateId);
    List<Certificate> getCertificatesByTagId(int tagId);
    void addInIntermediateTable(int certificateId, int tagId);
}
