package com.paypal.bfs.test.employeeserv.exceptions;

public final class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String EMPTY_OR_NULL_EMPLOYEE_OBJECT = "Employee object can't be null";
    public static final String EMPTY_OR_NULL_ADDRESS_OBJECT = "Address object can't be null";
    public static final String EMPTY_OR_NULL_FIRSTNAME = "FirstName can't be null or empty";
    public static final String EMPTY_OR_NULL_LASTNAME = "LastName can't be null or empty";
    public static final String EMPTY_OR_NULL_LINE1= "Line1 can't be null or empty";
    public static final String EMPTY_OR_NULL_CITY = "City can't be null or empty";
    public static final String EMPTY_OR_NULL_STATE = "State can't be null or empty";
    public static final String EMPTY_OR_NULL_COUNTRY = "Country can't be null or empty";
    public static final String EMPTY_OR_NULL_ZIPCODE = "Zipcode can't be null or empty";
    public static final String EMPTY_OR_NULL_DATE= "Date can't be null or empty";
    public static final String INVALID_DATE_PATTERN= "Invalid Date pattern. Date format should be DD/MM/YYYY";
    public static final String INVALID_EMPLOYEE_ID= "Employee id can't be String. It should be Valid number";
    public static final String NO_EMPLOYEE_OBJECT= "No Employee found with Id ";
    public static final String ERROR= "Error when processing the request ";
}
