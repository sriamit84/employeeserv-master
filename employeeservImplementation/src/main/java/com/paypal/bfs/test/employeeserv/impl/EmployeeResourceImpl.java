package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.exceptions.EmployeeApplicationException;
import com.paypal.bfs.test.employeeserv.jpa.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.jpa.model.EmployeesEntity;

import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.ERROR;
import static com.paypal.bfs.test.employeeserv.transformations.ObjectTransformationUtil.*;
import com.paypal.bfs.test.employeeserv.validators.EmployeeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.NO_EMPLOYEE_OBJECT;
//import static com.paypal.bfs.test.employeeserv.transformations.ModelToDAOTransformer.transformModelToEmployeeEntity;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeValidationService employeeValidationService;

    @Autowired
    private EmployeeRepository employeeRepo;


    @Override
    public ResponseEntity<Employee> employeeGetById(String id)  {
        try{
        Optional<EmployeesEntity> employeesEntityOptional = employeeRepo.findById(Integer.valueOf(id));
        if(employeesEntityOptional.isPresent()){
            Employee employee = transformDAOToEmployee(employeesEntityOptional.get());
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }else {
            throw new BadInputException(NO_EMPLOYEE_OBJECT+ id);
        }
        }catch(Exception e){
            throw new EmployeeApplicationException(ERROR+ e.getMessage());
        }

    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
       try {
            employeeValidationService.checkIfUserAlreadyExist(employee);
            employeeValidationService.validate(employee);
        EmployeesEntity employeesEntity = null;

            employeesEntity = transformModelToEmployeeEntity(employee);

        employeesEntity = employeeRepo.save(employeesEntity);
            Employee emp = transformDAOToEmployee(employeesEntity);
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } catch (InvocationTargetException e) {
           throw new EmployeeApplicationException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new EmployeeApplicationException(e.getMessage());
        }
    }


}
