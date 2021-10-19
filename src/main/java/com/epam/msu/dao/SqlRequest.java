package com.epam.msu.dao;

public class SqlRequest {

    public final static String getAllCertificates = "SELECT * FROM certificate";
    public final static String getCertificateById = "SELECT * FROM certificate where id=?";
    public final static String createNewCertificate = "INSERT INTO certificate VALUES (1,?,?,?,?,?,?)";
    public static final String updateCertificateById = "";

}
