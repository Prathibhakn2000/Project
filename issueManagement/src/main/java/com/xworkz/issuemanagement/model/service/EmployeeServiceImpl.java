package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public boolean employeeDetailsValidateAndSave(EmployeeDTO employeeDTO) {
        System.out.println("Running employeeDetailsValidateAndSave method");


        boolean save = this.employeeRepo.saveEmployeeDetails(employeeDTO);

        if (save) {

            System.out.println("Employee SignUp is Save successfully in service" + employeeDTO);

        } else {
            System.out.println("Employee SignUp is Not Save Successfully in service" + employeeDTO);

        }
        return true;


    }
}
