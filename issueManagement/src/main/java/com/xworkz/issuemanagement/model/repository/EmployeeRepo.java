package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeRepo {

    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    EmployeeDTO findByEmail(String email);


}
