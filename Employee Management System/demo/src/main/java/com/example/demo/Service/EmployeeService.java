package com.example.demo.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee viewOwnProfile(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
    }
}
