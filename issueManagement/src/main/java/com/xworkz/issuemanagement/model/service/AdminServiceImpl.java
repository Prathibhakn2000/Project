package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.emailSending.MailSending;
import com.xworkz.issuemanagement.model.repository.AdminRepo;
import com.xworkz.issuemanagement.util.PassWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements  AdminService {
    @Autowired
    private AdminRepo adminRepo;

    //password Encrypt
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    //private  EmailService emailService;
    private MailSending mailSending;


    @Override
    public boolean findByEmailAndPassword(String email, String password) {

        System.out.println("findByEmailAndPassword method in Service Implementation");

        AdminDTO data = adminRepo.findByEmailAndPassword(email, password);

        if (data != null) {
            System.out.println("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDTO> findById(SignUpDTO signUpDTO) {

        System.out.println("findById method in AdminServiceImpl..");
        List<SignUpDTO> dtoData = adminRepo.findById(signUpDTO);
        if (dtoData != null) {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO) {
        System.out.println("findById method in AdminServiceImpl(Raise complaintDTO)");

        List<RaiseComplaintDTO> data = adminRepo.findById(raiseComplaintDTO);
        if (data != null) {
            System.out.println("findById  data successful in AdminServiceImpl");
            return data;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl");
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city) {

        System.out.println("searchByComplaintType method running in AdminServiceImpl..");
        List<RaiseComplaintDTO> data = adminRepo.searchByComplaintTypeOrCity(complaintType, city);

        if (!data.isEmpty()) {
            System.out.println("searchByComplaintType successful in  AdminServiceImpl..");
            return data;
        } else {
            System.out.println("searchByComplaintType not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }


    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchComplaintByTypeAndCity method running in AdminServiceImpl..");
        List<RaiseComplaintDTO> data = adminRepo.searchByComplaintTypeAndCity(complaintType, city);

        if (!data.isEmpty()) {
            System.out.println("searchComplaintByTypeAndCity successful in  AdminServiceImpl..");
            return data;
        } else {
            System.out.println("searchComplaintByTypeAndCity not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public boolean validateAndsave(DepartmentDTO departmentDTO) {
        System.out.println("Running validateAndsave method");

        boolean save = this.adminRepo.save(departmentDTO);

        if (save)

            {
                System.out.println("Save department successfully" + departmentDTO);
                return true;
            }
            System.out.println("not saved department successfully" + departmentDTO);
             return false;
        }


    public List<DepartmentDTO> getAllDepartments() {
        return adminRepo.getAllDepartments(); // Retrieve all departments
    }


    @Override
    public void allocateDepartment(int complaintId, int deptId, String status) {
        System.out.println("Running  allocateDepartment method in adminserviceimpl...");
        // Delegate the department allocation to the repository
        adminRepo.allocateDepartment(complaintId, deptId, status);
    }

    @Override
    public boolean saveDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO) {
        System.out.println("Running saveDepartmentAdmin in AdminService...");

        String departmentadminpassword = PassWordGenerator.generatePassword();
        departmentAdminDTO.setPassword(passwordEncoder.encode(departmentadminpassword));


        boolean savedDepartmentAdmin = adminRepo.saveDepartmentAdmin(departmentAdminDTO);

        if (savedDepartmentAdmin) {
            System.out.println("Department Admin details saved successfully......" + departmentAdminDTO);
           // mailSending.sendDepartmentPassword(departmentAdminDTO); // Send plain password
            //password send to email
            departmentAdminDTO.setPassword(departmentadminpassword);
            mailSending.departmentAdminPassword(departmentAdminDTO);
            return true;
        }
        System.out.println("Department Admin details not saved ......");
        return false;
    }


    @Override
    public DepartmentAdminDTO findByDepartmentAdminEmailAndPassword(String email, String password) {
        System.out.println("Service: findByEmailIdAndPassword method...");
        DepartmentAdminDTO departmentAdminDTO = this.adminRepo.findByEmail(email);
        System.out.println("Service: data retrieved" + departmentAdminDTO);

        if (departmentAdminDTO != null && passwordEncoder.matches(password, departmentAdminDTO.getPassword())) {
            departmentAdminDTO.setPassword(null); // Clear the password for security
            return departmentAdminDTO;
        }

        System.out.println("Service: data not found or password mismatch");
        return null;
    }

}
