package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.*;

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
    boolean saveDepartments(DepartmentDTO departmentDTO);

    //getAllDepartments
    List<DepartmentDTO> getAllDepartments();

    //allocate the departments to complaints
    void allocateDepartment(int complaintId, int deptId, String status);

    // save department admin details
    boolean saveDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO);

    //  matching email and password and signin successfully
    DepartmentAdminDTO findByEmail(String email);
    DepartmentAdminDTO findByDepartmentAdminEmailAndPassword(String email, String password,String departmentType);


    //update the deptadmins details
    boolean updateDeptAdminDetails(DepartmentAdminDTO departmentAdminDTO);


    DepartmentDTO searchByDeptName(String departmentType);

    List<DepartmentDTO> getAllDepts();

    //findByUserDepartmentAdminId for admin view
    List<RaiseComplaintDTO> findByUSerComplaintType(String complaintType);











}
