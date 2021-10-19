package com.epam.msu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CertificateNotFoundException extends RuntimeException{

    public CertificateNotFoundException(){}

    public CertificateNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

    public CertificateNotFoundException(String message){
        super(message);
    }

    public CertificateNotFoundException(Throwable throwable){
        super(throwable);
    }


}
