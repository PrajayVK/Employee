package com.idfcbank.employee.controller;

import com.idfcbank.employee.model.Employee;
import com.idfcbank.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;


    @Test
    void createEmployee() {
        Employee newEmployee = new Employee(null, "Prajay", "V K", "prajay@idfcbank.com");
        when(employeeService.saveEmployee(newEmployee)).thenReturn(newEmployee);

        ResponseEntity<Employee> responseEntity = employeeController.createEmployee(newEmployee);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newEmployee, responseEntity.getBody());

    }

    @Test
    void getAllEmployees() {
        Employee newEmployee = new Employee(null, "Prajay", "V K", "prajay@idfcbank.com");
        Employee newEmployee2 = new Employee(null, "Prajay2", "V K", "prajay2@idfcbank.com");
        when(employeeService.getAllEmployees()).thenReturn(List.of(newEmployee,newEmployee2));

        ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(List.of(newEmployee,newEmployee2),responseEntity.getBody());

    }

    @Test
    void getEmployeeById() {
        Long employeeId =1L;
        Employee employee = new Employee(null,"Prajay","V K","prajay827742@gnail.com");
        employee.setEmployeeId(employeeId);
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));


        ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());

    }

    @Test
    void testUpdateEmployee() {
        Long employeeId = 1L;
        Employee employee = new Employee(null, "Prajay", "V K", "prajay827742@gmail.com");
        employee.setEmployeeId(employeeId);
        when(employeeService.updateEmployee(employeeId, employee)).thenReturn(employee);


        ResponseEntity<Employee> response = employeeController.updateEmployee(employeeId, employee);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());

    }

    @Test
    void testUpdateEmployee_NotFound() {

        Long employeeId = 1L;
        Employee employee = new Employee(null,"Prajay","V k","prajay827742@gmail.com");
        employee.setEmployeeId(employeeId);
        when(employeeService.updateEmployee(employeeId, employee)).thenThrow(new IllegalArgumentException());


        ResponseEntity<Employee> response = employeeController.updateEmployee(employeeId, employee);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }



    @Test
    void testDeleteEmployee() {

        Long employeeId = 1L;
        ResponseEntity<Void> response = employeeController.deleteEmployee(employeeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}