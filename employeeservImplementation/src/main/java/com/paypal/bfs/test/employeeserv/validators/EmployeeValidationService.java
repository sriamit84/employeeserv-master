package com.paypal.bfs.test.employeeserv.validators;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.jpa.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.jpa.model.EmployeesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.EMPTY_OR_NULL_ADDRESS_OBJECT;
import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.EMPTY_OR_NULL_EMPLOYEE_OBJECT;
import static com.paypal.bfs.test.employeeserv.validators.ValidationUtil.*;

@Service
public class EmployeeValidationService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void validate(Employee employee) {
        if(employee == null)
            throw new BadInputException(EMPTY_OR_NULL_EMPLOYEE_OBJECT);
        if(employee.getAddress() == null)
            throw new BadInputException(EMPTY_OR_NULL_ADDRESS_OBJECT);
        validateEmployeeFields(employee);
    }

    public void validateEmployeeFields(Employee employee) {
        VALIDATE_FIRSTNAME.accept(employee.getFirstName());
        VALIDATE_LASTNAME.accept(employee.getLastName());
        VALIDATE_DATE.accept(employee.getDateOfBirth());
        VALIDATE_DATE_FORMAT.apply(employee.getDateOfBirth());
        VALIDATE_LINE1.accept(employee.getAddress().getLine1());
        VALIDATE_CITY.accept(employee.getAddress().getCity());
        VALIDATE_STATE.accept(employee.getAddress().getState());
        VALIDATE_COUNTRY.accept(employee.getAddress().getCountry());
        VALIDATE_ZIPCODE.accept(employee.getAddress().getZipcode());
    }

    public void checkIfUserAlreadyExist(Employee employee) {
        Optional<EmployeesEntity> employeesEntity = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
        if(employeesEntity.isPresent()){
            throw new BadInputException("Employee already exist with firstname: "+ employee.getFirstName()
                    +" lastname: "+ employee.getLastName());
        }
    }
}