package com.idfcbank.employee.utility;

import com.idfcbank.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {

    public static ResponseEntity<Employee> created(Employee employee) {
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    public static ResponseEntity<List<Employee>> ok(List<Employee> employees) {
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    public static ResponseEntity<Employee> ok(Employee employee) {
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    public static ResponseEntity<Employee> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<Void> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

