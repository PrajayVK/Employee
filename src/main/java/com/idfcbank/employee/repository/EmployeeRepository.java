package com.idfcbank.employee.repository;

import com.idfcbank.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Employee> findByTeam(String team);
    List<Employee> findByRole(String role);
}



