package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.validators.EmployeeValidationService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.*;

@RunWith(SpringRunner.class)
public class EmployeeValidationServiceTest {

    @Mock
    BadInputException badInputException;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private EmployeeValidationService employeeValidationService;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testValidateWithNullEmployee() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_EMPLOYEE_OBJECT);
            employeeValidationService.validate(null);
    }

    @Test
    public void testValidateWithNullAndBlankEmployeeFirstName() {
        Address address = new Address();
        address.setLine1("Sarjapur Attibele Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("562107");
        Employee employee = new Employee();
        employee.setFirstName(null);
        employee.setLastName("MyLastName");
        employee.setDateOfBirth("21/06/1985");
        employee.setAddress(address);
        EmployeeValidationService employeeValidationServiceSpy= Mockito.spy(employeeValidationService);
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_FIRSTNAME);
        employeeValidationServiceSpy.validate(employee);
        Mockito.verify(employeeValidationServiceSpy, Mockito.times(1)).validateEmployeeFields(employee);
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_FIRSTNAME);
        employee.setFirstName("");
        employeeValidationServiceSpy.validate(employee);

    }
    @Test
    public void testValidateWithNullAddress() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_ADDRESS_OBJECT);
        Employee employee = new Employee();
        employee.setAddress(null);
        employeeValidationService.validate(employee);
        EmployeeValidationService employeeValidationServiceSpy= Mockito.spy(employeeValidationService);
        employeeValidationServiceSpy.validate(employee);
        Mockito.verify(employeeValidationServiceSpy, Mockito.times(0)).validateEmployeeFields(employee);
    }

    @Test
    public void testValidateWithValidInput() {
        Address address = new Address();
        address.setLine1("Sarjapur Attibele Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("562107");
        Employee employee = new Employee();
        employee.setFirstName("MyFirstName");
        employee.setLastName("MyLastName");
        employee.setDateOfBirth("21/06/1985");
        employee.setAddress(address);
        EmployeeValidationService employeeValidationServiceSpy= Mockito.spy(employeeValidationService);
        employeeValidationServiceSpy.validate(employee);
        Mockito.verify(employeeValidationServiceSpy, Mockito.times(1)).validateEmployeeFields(employee);
    }
}