package com.example.basic.service;

import com.example.basic.entity.Employee;

import java.util.List;

public interface employeeservice {
    List<Employee> getAllemployees();

    Employee saveStudent(Employee employee);

    Employee getEmployeebyId(Long id);

    Employee updateEmployee(Employee employee);

    void DeleteEmployee(Long id);

    public List<Employee> getByKeyword(String keyword);





}
