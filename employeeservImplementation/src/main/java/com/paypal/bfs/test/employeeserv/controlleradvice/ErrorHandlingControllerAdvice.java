package com.paypal.bfs.test.employeeserv.controlleradvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.exceptions.EmployeeApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.INVALID_EMPLOYEE_ID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {


  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ErrorResponse onConstraintValidationException(ConstraintViolationException e) {
    ErrorResponse error = new ErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      error.getErrors().add(new Error(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ResponseEntity<ErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ErrorResponse error = new ErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.getErrors().add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler(InvalidFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ResponseEntity<ErrorResponse> onInvalidFormatException(InvalidFormatException e) {
    ErrorResponse error = new ErrorResponse();
      error.getErrors().add(new Error(e.getPath().get(0).getFieldName(), e.getValue()+" is in incorrect format"));

    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler({ BadInputException.class})
  ResponseEntity<ErrorResponse> handleException(BadInputException exception) {
    ErrorResponse error = new ErrorResponse();
    error.getErrors().add(new Error(exception.getFieldName(), exception.getMessage()));
    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler(EmployeeApplicationException.class)
  ResponseEntity<ErrorResponse> handleEmployeeApplicationException(EmployeeApplicationException e){
    ErrorResponse error = new ErrorResponse();
    error.getErrors().add(new Error("", e.getMessage()));
    return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({ NumberFormatException.class})
  ResponseEntity<ErrorResponse> handleException() {
    ErrorResponse error = new ErrorResponse();
    error.getErrors().add(new Error("", INVALID_EMPLOYEE_ID));
    return new ResponseEntity<>(error, BAD_REQUEST);
  }


}