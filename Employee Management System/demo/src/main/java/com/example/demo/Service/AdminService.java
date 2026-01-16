package com.example.demo.Service;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import com.example.demo.FileStorage.FileStorageUtil;
import com.example.demo.Model.Employee;
import com.example.demo.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    public Employee addEmployee(Employee emp, MultipartFile photo) {
     try {
         String photoPath = fileStorageUtil.saveFile(photo);
         emp.setProfilePhotoPath(photoPath);
     } catch(IOException e){
         throw new RuntimeException("File upload failed");
     }
        emp.setPassword(encoder.encode(emp.getPassword()));
        emp.setRole("EMPLOYEE");
        return employeeRepository.save(emp);
    }

    public Employee updateEmployee(Long id, Employee emp) {
        Employee e = employeeRepository.findById(id).orElseThrow();
        e.setName(emp.getName());
        e.setDepartment(emp.getDepartment());
        e.setSalary(emp.getSalary());
        return employeeRepository.save(e);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById((id));
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
