package com.epam.msu.dao;

public final class SqlRequest {

    private SqlRequest(){}

    public final static String getAllCertificates = "SELECT * FROM certificate";
    public final static String getCertificateById = "SELECT * FROM certificate where id=?";
    public final static String createNewCertificate = "INSERT INTO certificate VALUES (1,?,?,?,?,?,?)";
    public final static String updateCertificateById = "";

}
