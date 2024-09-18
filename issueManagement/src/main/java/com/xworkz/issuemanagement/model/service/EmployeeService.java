package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface EmployeeService {

    boolean employeeDetailsValidateAndSave(EmployeeDTO employeeDTO);

    //List<EmployeeDTO> fetchEmployeeNamesByDepartment(String departmentType);

}
