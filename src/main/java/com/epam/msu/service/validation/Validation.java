package com.epam.msu.service.validation;

public class Validation {

    public static boolean isValidId(int id){
        boolean isValidId = id >= 0;
        return isValidId;
    }

}
