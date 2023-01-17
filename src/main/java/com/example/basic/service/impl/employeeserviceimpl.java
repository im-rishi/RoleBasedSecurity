package com.example.basic.service.impl;
import com.example.basic.entity.Employee;
import com.example.basic.repository.employeeRepository;
import com.example.basic.service.employeeservice;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class employeeserviceimpl implements employeeservice {
    private employeeRepository employeeRepository;

    public employeeserviceimpl(com.example.basic.repository.employeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllemployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveStudent(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeebyId(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void DeleteEmployee(Long id) {employeeRepository.deleteById(id);}

    @Override
    public List<Employee> getByKeyword(String keyword) {
        return employeeRepository.findByKeyword(keyword);
    }


}

