package com.sudhar.example.controller;

import com.sudhar.example.entity.Employee;
import com.sudhar.example.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/listPagedData")
    public Page<Employee> listPagedData(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @PostMapping("/add-data")
    public ResponseEntity<Void> addData() {
        final Employee e1 = new Employee("sudhar", "euc");
        final Employee e2 = new Employee("san", "euc");
        final Employee e3 = new Employee("thumatti", "r&d");

        employeeRepository.save(e1);
        employeeRepository.save(e2);
        employeeRepository.save(e3);

        employeeRepository.flush();

        logger.info("Employee data created.");

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/list/{limit}")
    public List<Employee> getByLimit(@PathVariable("limit") final String limit) {
        final List<Employee> employees = employeeRepository.getByPageNo(new PageRequest(Integer.valueOf(limit), 2));

        return employees;
    }
}
