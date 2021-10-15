package com.epam.msu.service.impl;

import com.epam.msu.dao.impl.CertificateDaoImpl;
import com.epam.msu.dao.impl.TagDaoImpl;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Tag;
import com.epam.msu.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDaoImpl certificateDao;
    private final TagDaoImpl tagDao;

    @Autowired
    public CertificateServiceImpl(CertificateDaoImpl certificateDao, TagDaoImpl tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateDao.getAllCertificates();
    }

    @Override
    public Certificate getCertificateById(int id) {
        if (id >= 0) {
            return certificateDao.getCertificateById(id);
        }
        return null;
    }

    @Override
    public void createNewCertificate(Certificate certificate) {
        Tag tag = certificate.getTag();
        int tagId = 0;
        int certificateId = 0;
        if (isUniqueTag(tag)) {
            tag = tagDao.createTag(tag);
            tagId = (int) tag.getId();
        } else {
            String tagName = tag.getName();
            Tag existsTag = tagDao.getTagByName(tagName);
            tagId = (int) existsTag.getId();
        }
        certificate = certificateDao.createNewCertificate(certificate);
        certificateId = (int) certificate.getId();
        certificateDao.addInIntermediateTable(certificateId, tagId);
    }

    private boolean isUniqueTag(Tag tag) {
        List<Tag> tags = tagDao.getAllTags();
        for (Tag el : tags) {
            if (el.getName().equals(tag.getName())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateCertificateById(Certificate certificate, int certificateId) {
        certificateDao.updateCertificateById(certificate, certificateId);
    }

    @Override
    public void deleteCertificateById(int certificateId) {
        certificateDao.deleteCertificateById(certificateId);
    }

    @Override
    public List<Certificate> getAllCertificatesByTagName(String tagName) {
        List<Certificate> certificates = null;
        Tag tag = tagDao.getTagByTagName(tagName);
        int tagId = (int) tag.getId();
        certificates = certificateDao.getCertificatesByTagId(tagId);
        return certificates;
    }
}
