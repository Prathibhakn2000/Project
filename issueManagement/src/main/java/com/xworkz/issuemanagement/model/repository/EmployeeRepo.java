package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;

public interface EmployeeRepo {

    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);
}
