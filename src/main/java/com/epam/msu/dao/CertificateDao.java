package com.epam.msu.dao;

import com.epam.msu.entity.Certificate;

import javax.sql.DataSource;

public interface CertificateDao {
    void setDataSource(DataSource dataSource);
    Certificate getCertificateById(int id);
    void createNewCertificate(Certificate certificate);
    void updateCertificateById(int certificateId);
    void deleteCertificateById(int certificateId);
}
