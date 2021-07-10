package com.paypal.bfs.test.employeeserv.exceptions;

public class EmployeeApplicationException extends RuntimeException{

    public EmployeeApplicationException(){
        super();
    }
    public EmployeeApplicationException(String message){
        super(message);
    }

    public EmployeeApplicationException(String message, Throwable t){
        super(message,t);
    }
}
