package com.example.demo.Controller;

import com.example.demo.Model.Employee;
import com.example.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private final tools.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Employee addEmployee(
            @RequestPart("employee") String empJson,
            @RequestPart("photo") MultipartFile photo
    ) throws IOException {

        Employee emp = objectMapper.readValue(empJson, Employee.class);
        return adminService.addEmployee(emp, photo);
    }


    @PutMapping("/update/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee emp
    ) {
        return adminService.updateEmployee(id, emp);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        adminService.deleteEmployee(id);
    }


    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return adminService.getAllEmployee();
    }
}
