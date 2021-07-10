package com.paypal.bfs.test.employeeserv.jpa;

import com.paypal.bfs.test.employeeserv.jpa.model.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesEntity,Integer> {
    Optional<EmployeesEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
