package com.example.demo.Service;

import com.example.demo.Config.JwtUtil;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.LoginResponse;
import com.example.demo.Model.Employee;
import com.example.demo.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request){

        Employee emp  = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid email"));

        if(!encoder.matches(request.getPassword(), emp.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtUtil.generateToken(emp.getEmail(),emp.getRole());
        return new LoginResponse(token);
    }
}
