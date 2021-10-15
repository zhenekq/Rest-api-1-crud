package com.epam.msu.dao.impl;

import com.epam.msu.dao.CertificateDao;
import com.epam.msu.dao.SqlRequest;
import com.epam.msu.dao.mapper.CertificateMapper;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return jdbcTemplate.query(SqlRequest.getAllCertificates, new CertificateMapper());
    }

    @Override
    public List<Certificate> getCertificatesByTagId(int tagId) {
        return jdbcTemplate.query("select id, name, description, price, duration, create_date, last_update_date from certificate join certificate_tag on certificate_tag.certificate_id = certificate.id where tag_id = ?",
                new Object[]{tagId}, new BeanPropertyRowMapper<>(Certificate.class));
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
    public Certificate createNewCertificate(Certificate certificate) {
        jdbcTemplate.update("INSERT INTO certificate (name, description, price, duration, create_date, last_update_date) VALUES (?,?,?,?,?,?)",
                certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate());
        certificate = getLastAddedCertificate();
        return certificate;
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
        jdbcTemplate.update("DELETE FROM certificate where id = ?", certificateId);
    }

    @Override
    public void addInIntermediateTable(int certificateId, int tagId) {
        jdbcTemplate.update("INSERT INTO certificate_tag (certificate_id, tag_id) VALUES (?, ?)",
                certificateId, tagId);
    }


    private Certificate getLastAddedCertificate(){
        return jdbcTemplate.query("SELECT * FROM certificate order by id desc limit 1", new Object[]{}, new CertificateMapper())
                .stream()
                .findAny()
                .orElse(null);
    }
}
