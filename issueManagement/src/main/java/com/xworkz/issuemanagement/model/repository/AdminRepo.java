package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminRepo {

    AdminDTO findByEmailAndPassword(String email, String password);


    //Admin can view all user data
    List<SignUpDTO> findById(SignUpDTO signUpDTO);

    //Admin can view Raise Complaint details
    List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO);


    //search by complaint type or city
    List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city);

    //search by complaint type and city
    List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city);

    //save department
    boolean save(DepartmentDTO departmentDTO);


}
