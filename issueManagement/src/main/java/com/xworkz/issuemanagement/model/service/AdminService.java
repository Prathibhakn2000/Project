package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.*;

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
    DepartmentAdminDTO findByDepartmentAdminEmailAndPassword(String email, String password,String departmentType);

    //Lock account when give 3 times wrong Password
    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    //when account lock after 3 attemps then forgot password it will unlock
    void unlockAccount(String email);

   //forgot password
    boolean adminForgotPassword(String email);

   // change password
    boolean adminChangePassword(String email, String oldPassword, String newPassword, String confirmPassword);

    //
    DepartmentDTO searchByDeptName(String departmentType);

    List<DepartmentDTO> getAllDepts();

    //List<DepartmentAdminDto> findByUserDepartmentAdminId();

    List<RaiseComplaintDTO> findByUSerComplaintType(String complaintType);


    //getAllemployee
    List<EmployeeDTO> getParticularEmployees(String departmentType);

    void allocateEmployee(int complaintId, int employeeId);








}
