package com.epam.msu.dao.jdbc;

import com.epam.msu.dao.CertificateDao;
import com.epam.msu.entity.Certificate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTemplateCertificateDaoImpl implements CertificateDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Certificate getCertificateById(int id) {
        return null;
    }

    @Override
    public void createNewCertificate(Certificate certificate) {
        String SQL = "INSERT INTO certificate (name, description, price, duration, )";
    }

    @Override
    public void updateCertificateById(int certificateId) {

    }

    @Override
    public void deleteCertificateById(int certificateId) {

    }
}
