package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

 @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public boolean validateAndSaveEmployeeDetails(EmployeeDTO employeeDTO) {
        System.out.println("Running employeeDetailsValidateAndSave method");


        boolean save = this.employeeRepo.saveEmployeeDetails(employeeDTO);

        if (save) {

            System.out.println("Employee SignUp is Save successfully in service" + employeeDTO);

        } else {
            System.out.println("Employee SignUp is Not Save Successfully in service" + employeeDTO);

        }
        return true;


    }

    //to check whether emailId exists or not in database
    @Override
    public EmployeeDTO findByEmail(String email) {

        System.out.println("findByEmail method running EmployeeServiceImpl..");


        System.out.println ("Your EmailId : "+ "email");
        EmployeeDTO employeeDTO = employeeRepo.findByEmail(email);

        if (employeeDTO != null) {
            System.out.println("EmailId exists in database");
            return employeeDTO;
        } else {
            System.out.println("EmailId not exists in database");
        }


        return null;
    }

    @Override
    //@Transactional
    public boolean deleteAllocatedEmployee(int employeeId, int complaintId) {


        // Update the employee status to 'inactive'
        boolean updateSuccess = employeeRepo.updateEmployeeStatusToInActive(employeeId,complaintId);

        return updateSuccess;
    }
    //return false;
}



