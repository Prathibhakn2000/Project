package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.emailSending.MailSending;
import com.xworkz.issuemanagement.model.repository.AdminRepo;
import com.xworkz.issuemanagement.util.PassWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static com.xworkz.issuemanagement.util.PassWordGenerator.generatePassword;

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

        boolean save = this.adminRepo.saveDepartments(departmentDTO);

        if (save) {
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

        String departmentadminpassword = generatePassword();
        departmentAdminDTO.setPassword(passwordEncoder.encode(departmentadminpassword));


        boolean savedDepartmentAdmin = adminRepo.saveDepartmentAdmin(departmentAdminDTO);

        if (savedDepartmentAdmin) {
            System.out.println("Department Admin details saved successfully......" + departmentAdminDTO);
            // mailSending.sendDepartmentPassword(departmentAdminDTO); // Send plain password
            //password send to email
            departmentAdminDTO.setPassword(departmentadminpassword);
            mailSending.sendDeptAdminPassword(departmentAdminDTO);
            return true;
        }
        System.out.println("Department Admin details not saved ......");
        return false;
    }


    @Override
    public DepartmentAdminDTO findByDepartmentAdminEmailAndPassword(String email, String password,String departmentType) {
        System.out.println("Service: findByEmailIdAndPassword method...");
        DepartmentAdminDTO departmentAdminDTO = this.adminRepo.findByEmail(email);
        System.out.println("Service: data retrieved" + departmentAdminDTO);

        if (departmentAdminDTO != null && passwordEncoder.matches(password, departmentAdminDTO.getPassword())&& !departmentAdminDTO.isAccountLocked() && departmentAdminDTO.getDepartmentType().equals(departmentType)) {
            departmentAdminDTO.setPassword(null); // Clear the password for security
            return departmentAdminDTO;
        }

        System.out.println("Service: data not found or password mismatch");
        return null;
    }

    //Lock account when give 3 times wrong Password
    @Override
    public void incrementFailedAttempts(String email) {
        DepartmentAdminDTO admin = adminRepo.findByEmail(email);
        if (admin != null) {
            int attempts = admin.getFailedAttempt() + 1;
            admin.setFailedAttempt(attempts);
            if (attempts >= 3) {
                admin.setAccountLocked(true);
            }
            adminRepo.updateDeptAdminDetails(admin);
        }

    }

    @Override
    public int getFailedAttempts(String email) {
        DepartmentAdminDTO admin = adminRepo.findByEmail(email);
        return (admin != null) ? admin.getFailedAttempt() : 0;
    }

    @Override
    public void resetFailedAttempts(String email) {
        DepartmentAdminDTO admin = adminRepo.findByEmail(email);
        if (admin != null) {
            admin.setFailedAttempt(0); //false
            adminRepo.updateDeptAdminDetails(admin);
        }

    }

    @Override
    public void lockAccount(String email) {
        DepartmentAdminDTO admin = adminRepo.findByEmail(email);
        if (admin != null) {
            admin.setAccountLocked(true);
            adminRepo.updateDeptAdminDetails(admin);
        }

    }

    //forgot password when accout lock then it will accout unlock
    @Override
    public void unlockAccount(String email) {
        DepartmentAdminDTO admin = adminRepo.findByEmail(email);
        if (admin != null) {
            admin.setAccountLocked(false);
            adminRepo.updateDeptAdminDetails(admin);
        }

    }

    @Override
    public boolean adminForgotPassword(String email) {
        System.out.println("Running forgotPassword in ForgetPasswordServiceImpl.. ");
        DepartmentAdminDTO departmentAdminDTO = adminRepo.findByEmail(email);
        if (departmentAdminDTO != null) {
            //Generating Random password and sending it...
            String newPassword = generatePassword();
            departmentAdminDTO.setPassword(passwordEncoder.encode(newPassword));
            // signupDto.setPassword(encoder.encode(newPassword));
            adminRepo.updateDeptAdminDetails(departmentAdminDTO);
            departmentAdminDTO.setPassword(newPassword);
            //sendPassword(departmentAdminDTO);
            mailSending.adminSendForgotPassword(departmentAdminDTO);

            //Reset failed attempts
            this.resetFailedAttempts(email);
            this.unlockAccount(email);
            System.out.println("Data is existing " + departmentAdminDTO);

            return true;

        } else {
            System.out.println(" Data is not existing" + departmentAdminDTO);
        }
        return false;
    }

    @Override
    public boolean adminChangePassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("Attempting to change password for email: " + email);

        // Step 1: Check if newPassword matches confirmPassword
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New password and confirm password do not match.");
            return false;
        }

        // Step 2: Retrieve departmentAdminDto based on emailId
        if (adminRepo == null) {
            System.out.println("adminRepo is null");
            return false;
        }

        DepartmentAdminDTO departmentAdminDTO = this.adminRepo.findByEmail(email);
        if (departmentAdminDTO == null) {
            System.out.println("User with email " + email + " not found.");
            return false;
        }

        String storedPassword = departmentAdminDTO.getPassword();
        System.out.println("Stored password: " + storedPassword);

        // Step 3: Verify oldPassword matches the stored password
        if (passwordEncoder == null) {
            System.out.println("passwordEncoder is null");
            return false;
        }

        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            System.out.println("Old password verification failed for email: " + email);
            return false; // Old password doesn't match
        }

        // Step 4: Encode and update the new password in SignupDto
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        departmentAdminDTO.setPassword(encodedNewPassword);

        // Step 5: Save the updated password in the repository
        if (adminRepo == null) {
            System.out.println("adminRepo is null before updateDeptAdminDetails");
            return false;
        }

        boolean save = adminRepo.updateDeptAdminDetails(departmentAdminDTO);

        // Step 6: Send email notification if password update was successful
        if (save) {
            System.out.println("Password updated successfully for email: " + email);
            try {
                if (mailSending == null) {
                    System.out.println("mailSending is null");
                    return false;
                }
                mailSending.adminSendresetPassword(departmentAdminDTO, newPassword);
                return true; // Password successfully updated and email sent
            } catch (MailException e) {
                e.printStackTrace();
                return false; // Indicate failure if email sending failed
            }
        }

        return false; // Password update failed
    }

    @Override
    public DepartmentDTO searchByDeptName(String departmentType) {
        System.out.println("Running searchByDeptType method in AdminServiceImpl... ");
        DepartmentDTO departmentDTO=adminRepo.searchByDeptName(departmentType);
        if(departmentDTO!=null)
        {
            System.out.println("FindBy Department Name successfully"+departmentType);
            return departmentDTO;
        }

        System.out.println("FindBy Department Name successfully"+departmentType);
        return null;

    }

    @Override
    public List<DepartmentDTO> getAllDepts() {
        return adminRepo.getAllDepartments(); // Retrieve all departments
    }

    @Override
    public List<RaiseComplaintDTO> findByUSerComplaintType(String complaintType) {

        System.out.println("findByUSerComplaintType method in AdminServiceImpl..");
        List<RaiseComplaintDTO> dtoData = adminRepo.findByUSerComplaintType( complaintType);
        if (dtoData != null) {
            System.out.println("findByComplaintType data successful in AdminServiceImpl..");
            return dtoData;
        } else {
            System.out.println("findByComplaintType data not successful in AdminServiceImpl..");
        }

        return Collections.emptyList();
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return adminRepo.getAllEmployees();
    }

    @Override
    public void allocateEmployee(int complaintId, int employeeId, String status) {
        System.out.println("Running  allocateDepartment method in adminserviceimpl...");
        // Delegate the employee allocation to the repository
        adminRepo.allocateEmployee(complaintId, employeeId, status);

    }

}



