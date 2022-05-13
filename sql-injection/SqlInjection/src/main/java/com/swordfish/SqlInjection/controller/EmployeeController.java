package com.swordfish.SqlInjection.controller;

import com.swordfish.SqlInjection.model.Employee;
import com.swordfish.SqlInjection.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/filterEmployee")
    public List<List> filterByUsername(@RequestParam(value = "name") String name) {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        employeeService.uploadFile(multipartFile);
    }
}