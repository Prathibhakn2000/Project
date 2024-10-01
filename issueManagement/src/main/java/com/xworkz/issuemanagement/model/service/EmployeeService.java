package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface EmployeeService {

    boolean validateAndSaveEmployeeDetails(EmployeeDTO employeeDTO);

    //it is used to check email is present in DB
    EmployeeDTO findByEmail(String email);


    boolean deleteAllocatedEmployee(int employeeId, int complaintId);
}
