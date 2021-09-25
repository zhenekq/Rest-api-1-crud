package com.epam.msu.dao.impl;

import com.epam.msu.dao.CertificateDao;
import com.epam.msu.dao.SqlRequest;
import com.epam.msu.dao.mapper.CertificateMapper;
import com.epam.msu.entity.Certificate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificateDaoImpl implements CertificateDao {

    private final JdbcTemplate jdbcTemplate;

    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return jdbcTemplate.query(SqlRequest.getAllCertificates, new CertificateMapper());
    }

    @Override
    public Certificate getCertificateById(int id) {
        return jdbcTemplate.query(SqlRequest.getCertificateById,
                        new Object[]{id}, new CertificateMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void createNewCertificate(Certificate certificate) {
        jdbcTemplate.update("INSERT INTO certificate VALUES (?,?,?,?,?,?,?)",
                certificate.getId(),
                certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate());
    }

    @Override
    public void updateCertificateById(Certificate certificate, int certificateId) {
        jdbcTemplate.update("UPDATE certificate set name=?, description=?, price=?,duration=?,create_date=?,last_update_date=? where id = ?",
                certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate(), certificateId);
    }

    @Override
    public void deleteCertificateById(int certificateId) {

    }
}
