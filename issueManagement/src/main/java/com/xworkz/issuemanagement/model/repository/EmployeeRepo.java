package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeRepo {

    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    //it is used to check email is present in DB
    EmployeeDTO findByEmail(String email);




    boolean updateEmployeeStatusToInActive(int employeeId,int complaintId);


}
