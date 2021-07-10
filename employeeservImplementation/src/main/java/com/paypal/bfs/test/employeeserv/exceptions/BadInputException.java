package com.paypal.bfs.test.employeeserv.exceptions;

public class BadInputException extends RuntimeException{

    private String fieldName;

    public BadInputException(String message) {
        super(message);
    }


    public BadInputException(String message,String fieldName) {
        super(message);
        this.fieldName=fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
