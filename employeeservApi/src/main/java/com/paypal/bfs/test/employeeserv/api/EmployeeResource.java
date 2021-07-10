package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.POST;

/**
 * Interface for employee resource operations.
 */
@RequestMapping("/v1/bfs/employees")
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @GetMapping("{id}")
    ResponseEntity<Employee> employeeGetById(@Valid @PathVariable("id") final String id);


    @PostMapping
    ResponseEntity<Employee> createEmployee(@Valid @RequestBody final Employee employee);
}
