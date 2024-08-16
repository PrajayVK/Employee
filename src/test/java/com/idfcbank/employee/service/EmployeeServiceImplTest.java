package com.idfcbank.employee.service;

import com.idfcbank.employee.model.Employee;
import com.idfcbank.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    void saveEmployee() {
        Employee newEmployee = new Employee(null,"Prajay","V K","prajay827742@gmail.com");
        when(employeeRepository.save(newEmployee)).thenReturn(new Employee(1L,"Prajay","V K","prajay827742@gmail.com"));
        Employee savedEmployee = employeeService.saveEmployee(newEmployee);
        assertNotNull(savedEmployee, "The saved employee should not be null");
        assertNotNull(savedEmployee.getId(), "The saved employee should have an ID");
        assertEquals("Prajay", savedEmployee.getFirstName(), "The name of the saved employee should match");
    }

    @Test
    void getAllEmployees() {
        Employee newEmployee = new Employee(null,"Prajay","V K","prajay827742@gmail.com");
        Employee newEmployee2 = new Employee(null,"Prajay2","V K","prajay2827742@gmail.com");
        when(employeeRepository.findAll()).thenReturn(List.of(newEmployee, newEmployee2));
        List<Employee> employeeList = employeeService.getAllEmployees();
        assertEquals(2,employeeList.size(),"Number of employees in the list should be two");
    }

    @Test
    void getEmployeeById() {
        Optional<Employee> optionalEmployee = Optional.of(new Employee(1L, "Prajay", "Developer", "IT"));
        when(employeeRepository.findById(1L)).thenReturn(optionalEmployee);

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
        assertTrue(foundEmployee.isPresent(), "An employee should be found with ID 1");
        foundEmployee.ifPresent(e -> assertEquals("Prajay", e.getFirstName(), "The employee's name should match"));
    }
}