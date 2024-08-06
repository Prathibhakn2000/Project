package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminService {
     boolean findByEmailAndPassword(String email,String password);


    //admin can view all user details(signUp details)
    List<SignUpDTO> findById(SignUpDTO signUpDTO);


    //admin can view all user raise Complaint details(RaiseComplaintDTO)
    List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO);


    //admin search by based on complaint type
    List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType , String city) ;

    //admin Search by based on city
   // List<RaiseComplaintDTO> searchComplaintByCity(RaiseComplaintDTO raiseComplaintDTO) ;


    //search complaint by type and city
    List<RaiseComplaintDTO>searchByComplaintTypeAndCity(String complaintType , String city);

    //save departments
    boolean validateAndsave(DepartmentDTO departmentDTO);

    //getAllDepartments
    List<DepartmentDTO> getAllDepartments();

    //allocate departments to complaint
    void allocateDepartment(int complaintId, int deptId,String status);


    //from here im going to save department admin details
    boolean saveDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO);

    //matching the email and password
    DepartmentAdminDTO findByDepartmentAdminEmailAndPassword(String email, String password);



}
