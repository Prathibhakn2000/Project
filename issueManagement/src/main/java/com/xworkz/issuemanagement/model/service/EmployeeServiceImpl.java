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



//    @Override
//    public List<EmployeeDTO> fetchEmployeeNamesByDepartment(String departmentType)
//    {
//        System.out.println("fetchEmployeeName method running in EmployeeServiceImpl..");
//
//        List<EmployeeDTO> fetchEmployeeName = employeeRepo.fetchEmployeeNamesByDepartment(departmentType);
//
//        if (fetchEmployeeName != null) {
//            System.out.println("EmployeeName fetched successfully.. ");
//            return fetchEmployeeName;
//        } else {
//            System.out.println("EmployeeName not fetched successfully..");
//        }
//
//        return fetchEmployeeName;
//
//    }
}
