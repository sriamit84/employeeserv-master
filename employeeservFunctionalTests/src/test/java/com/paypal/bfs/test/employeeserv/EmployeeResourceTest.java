package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeResourceTest {
    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate restTemplate;

    Employee employee;

    @Before
    public void init(){
        Address address = new Address();
        address.setLine1("Sarjapur Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("562107");
        employee = new Employee();
        employee.setFirstName("MyFirstName");
        employee.setLastName("MyLastName");
        employee.setDateOfBirth("21/06/1997");
        employee.setAddress(address);
    }
    @Test
    public void testCreateEmployee(){
        HttpEntity<Employee> entity = new HttpEntity<>(employee);
        employee.setLastName("myLastName");
        ResponseEntity<Employee> response = restTemplate.exchange(
                getUrl.apply("v1/bfs/employees"),
                HttpMethod.POST, entity, Employee.class);

        Employee body = response.getBody();
        assertNotNull(body);
        assertEquals(body.getLastName(), "myLastName");
    }

    @Test
    public void testGetEmployee(){
        HttpEntity<Employee> entity = new HttpEntity<>(employee);

        //make a post call to insert data into db
        ResponseEntity<Employee> createResponse = restTemplate.exchange(
                getUrl.apply("v1/bfs/employees"),
                HttpMethod.POST, entity, Employee.class);
        assertNotNull(createResponse.getBody());

        //make a get call
        ResponseEntity<Employee> getResponse = restTemplate.exchange(
                getUrl.apply("v1/bfs/employees/"+createResponse.getBody().getId()),
                HttpMethod.GET, entity, Employee.class);

        assertNotNull(getResponse.getBody());
        assertEquals(getResponse.getBody().getId(), createResponse.getBody().getId());
        assertEquals(getResponse.getBody().getFirstName(), createResponse.getBody().getFirstName());
    }



    public Function<String,String> getUrl = (uri)-> {
        return "http://localhost:" + port + uri;
    };
}
