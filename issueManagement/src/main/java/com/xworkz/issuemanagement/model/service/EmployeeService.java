package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface EmployeeService {

    boolean validateAndSaveEmployeeDetails(EmployeeDTO employeeDTO);

    EmployeeDTO findByEmail(String email);
}
