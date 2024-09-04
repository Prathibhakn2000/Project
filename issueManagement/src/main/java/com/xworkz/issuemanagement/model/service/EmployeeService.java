package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface EmployeeService {

    boolean employeeDetailsValidateAndSave(EmployeeDTO employeeDTO);
}
