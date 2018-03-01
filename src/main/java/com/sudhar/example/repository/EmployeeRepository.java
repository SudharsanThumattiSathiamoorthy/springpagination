package com.sudhar.example.repository;

import com.sudhar.example.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("from Employee order by name DESC")
    List<Employee> getByPageNo(Pageable pageable);
}