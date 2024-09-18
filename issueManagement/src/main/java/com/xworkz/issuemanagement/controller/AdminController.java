package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession httpSession;

    public AdminController() {
        System.out.println("No parameter constructor created for AdminController...");
    }

    @PostMapping("admin")
    public String adminDetails(AdminDTO adminDTO, @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes, Model model) {
        System.out.println("adminDetails method in AdminController");


        boolean data = adminService.findByEmailAndPassword(email, password);

        if (data) {
            System.out.println("findByEmailAndPassword successful in AdminController..");
            redirectAttributes.addFlashAttribute("adminMessage", "Login successful");

            model.addAttribute("AdminProfilePageMessage", "Welcome to Admin profile");
            return "AdminProfile";
            //return "redirect:/adminPage";
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminController");
            redirectAttributes.addFlashAttribute("errorAdminMessage", "Failed to login. Please check your email and password.");
            return "redirect:/admin";
        }
    }


//    @GetMapping("adminPage")
//    public String showAdminPage() {
//        return "AdminPage";
//    }


    //view user details(SignUp details)
    @GetMapping("view-user-details")
    public String viewUserDetails(SignUpDTO signUpDTO, Model model) {
        System.out.println("viewUserDetails method in AdminController..");
        List<SignUpDTO> signUpDtoData = adminService.findById(signUpDTO);

        if (signUpDtoData != null) {
            System.out.println("view-user-details successful in AdminController..");
            model.addAttribute("ViewUserDetails", signUpDtoData);
            return "AdminViewUserDetails";


        } else {
            System.out.println("view-user-details not  successful in AdminController..");
        }
        return "AdminViewUserDetails";
    }


    //view Raise complaint details
    @GetMapping("view-raise-complaint")
    public String viewRaiseComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model) {
        System.out.println("viewUserDetails method running in AdminController");

        List<RaiseComplaintDTO> viewData = adminService.findById(raiseComplaintDTO);
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("viewRaiseComplaint", viewData);
        if (viewData != null) {
            System.out.println("View raise complaint data successful in AdminController");
            model.addAttribute("departments", departments);
            return "AdminViewRaiseComplaintDetails";
        } else {
            System.out.println("View raise complaint data not successful in AdminController.");
        }
        return "AdminViewRaiseComplaintDetails";
    }


    @PostMapping("search-complaint")
    public String searchUserComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model) {
        System.out.println("viewUserDetails method in AdminController..");

        List<RaiseComplaintDTO> listOfComplaintTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        if (!listOfComplaintTypeAndCity.isEmpty()) {
            model.addAttribute("viewRaiseComplaint", listOfComplaintTypeAndCity);

            model.addAttribute("departments", departments);

            return "AdminViewRaiseComplaintDetails";
        } else {
            List<RaiseComplaintDTO> listOfComplaintTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
            if (!listOfComplaintTypeOrCity.isEmpty()) {
                model.addAttribute("viewRaiseComplaint", listOfComplaintTypeOrCity);
                model.addAttribute("departments", departments);

                return "AdminViewRaiseComplaintDetails";
            }
        }

        return "AdminViewRaiseComplaintDetails";
    }


    //save depts
    @PostMapping("/add-department")
    public String signUp(@Valid DepartmentDTO departmentDTO, Model model) {
        System.out.println("department method running in department Controller..");
        System.out.println("DepartmentDTO ;" + departmentDTO);

        boolean dataValid = this.adminService.validateAndsave(departmentDTO);
        if (dataValid) {
            System.out.println("department registration successful in departmentController:" + departmentDTO);
            model.addAttribute("message", "Department Add Successfully ");
            return "redirect:/department"; //getmapping action retuns

        } else {
            System.out.println("department registration not successful in departmentController:" + departmentDTO);
        }

        return "redirect:/department"; //getmapping action retuns

    }

    //when data enter and refresh it save double time in database to avoid use this
    @GetMapping("department")
    public String saveDept(DepartmentDTO departmentDTO, Model model) {
        model.addAttribute("message", "Department Add Successfully ");
        return "AddDepartments";
    }

    //allocate departs in complaint view
    @PostMapping("/allocate-department")
    public String allocateDepartment(
            @RequestParam("complaintId") int complaintId,
            @RequestParam("deptId") int deptId,
            @RequestParam("status") String status,
            Model model
    ) {
        try {
            System.out.println("Running allocate department");

            // Call the service method to allocate department
            adminService.allocateDepartment(complaintId, deptId, status);
            System.out.println("complaintId" + complaintId);
            System.out.println("deptId" + deptId);
            model.addAttribute("successMessage", "Department allocated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to allocate department. Please try again.");
            e.printStackTrace();
        }
        return "AdminViewRaiseComplaintDetails";

    }

//    @PostMapping("/department-admins")
//    public String registerDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO, Model model) {
//
//        DepartmentDTO departmentDTO=adminService.searchByDeptName(departmentAdminDTO.getDepartmentType());
//        departmentAdminDTO.setDeptId(departmentDTO);
//
//        System.out.println("Running signInDepartmentAdmin method in AdminController...");
//        boolean deptAdminDto = adminService.saveDepartmentAdmin(departmentAdminDTO);
//        if (deptAdminDto) {
//            model.addAttribute("signInsuccess", "Successfully logined" + departmentAdminDTO);
//            return "AddDepartmentAdmins";
//        }
//        model.addAttribute("signInFailed", "login failed" + departmentAdminDTO);
//        return "redirect:/get-all-departments";
//    }
//
//    @GetMapping("/get-all-departments")
//    public String getform(Model model){
//        List<DepartmentDTO> departments = adminService.getAllDepartments();
//        model.addAttribute("departments",departments);
//        return "AddDepartmentAdmins";
//    }


    @PostMapping("/department-admins")
    public String registerDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO, Model model, RedirectAttributes redirectAttributes) {

        DepartmentDTO departmentDTO = adminService.searchByDeptName(departmentAdminDTO.getDepartmentType());
        departmentAdminDTO.setDeptId(departmentDTO);

        System.out.println("Running signInDepartmentAdmin method in AdminController...");
        boolean deptAdminDto = adminService.saveDepartmentAdmin(departmentAdminDTO);

        List<DepartmentDTO> departments = adminService.getAllDepartments(); // Load departments
        model.addAttribute("departments", departments); // Add departments to the model

        if (deptAdminDto) {
            redirectAttributes.addFlashAttribute("signInsuccess", "Successfully logined ");
            return "redirect:/get-all-departments";
        }
        redirectAttributes.addFlashAttribute("signInFailed", "Login failed ");
        return "redirect:/get-all-departments";  // Stay on the same page instead of redirecting
    }

    @GetMapping("/get-all-departments")
    public String getform(Model model) {
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "AddDepartmentAdmins";
    }


    @PostMapping("/admin-sign-in")
    public String signInDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO, Model model, HttpSession httpSession, @RequestParam String email, RedirectAttributes redirectAttributes) {
        //System.out.println("departPartAdmin"+departmentAdminDto);
        System.out.println("Running signInDepartmentAdmin method in AdminController...");
        DepartmentAdminDTO adminDTO = adminService.findByDepartmentAdminEmailAndPassword(departmentAdminDTO.getEmail(), departmentAdminDTO.getPassword(), departmentAdminDTO.getDepartmentType());
        if (adminDTO != null && !adminDTO.isAccountLocked()) {
            adminService.resetFailedAttempts(departmentAdminDTO.getEmail());
            model.addAttribute("msg1", "Successfully logined");

            //email id in dropdown
            httpSession.setAttribute("email", departmentAdminDTO.getEmail());

            httpSession.setAttribute("deptadmin", adminDTO);


            return "DepartmentAdminProfile";

        } else {
            adminService.incrementFailedAttempts(departmentAdminDTO.getEmail());
            int failedAttempts = adminService.getFailedAttempts(departmentAdminDTO.getEmail());
            System.out.println("Failed attempts for " + departmentAdminDTO.getEmail() + ": " + failedAttempts);
            if (failedAttempts >= 3) {
                adminService.lockAccount(departmentAdminDTO.getEmail()); // Lock account after 3 failed attempts
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
                return "DepartmentAdminSignIn";
            } else {
                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                model.addAttribute("accountLocked", false);
                System.out.println(" data are not exists" + departmentAdminDTO);
                return "redirect:/getdeptfor-signin";
            }

        }
    }

    @GetMapping("/getdeptfor-signin")  //action in href
    public String getdept(Model model) {
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departmentsforsignin", departments);
        //model.addAttribute("error", "Your account is locked due to too many failed attempts.");
        return "DepartmentAdminSignIn";
    }


    @PostMapping("/deptadmin-forgot-password")
    public String adminForgotPassword(@RequestParam("email") String email, Model model) {
        System.out.println("Running forgetPassword method in ForgetPasswordController...");
        boolean success = adminService.adminForgotPassword(email);
        if (success) {
            model.addAttribute("forgotMessage", "A new Password has been sent to your email");
            return "redirect:/getdeptfor-signin";
        } else {
            model.addAttribute("forgotError", "Email address not found.");
            return "DepartmentAdminForgotPassword";
        }
    }


    @PostMapping("/deptadmin-reset-password")
    public String passwordReset(@RequestParam("email") String email, @RequestParam("oldpassword") String oldPassword,
                                @RequestParam("newpassword") String newPassword, @RequestParam("confirmpassword") String confirmPassword, Model model) {
        System.out.println("email: " + email + ", old: " + oldPassword + ", new: " + newPassword + ", con: " + confirmPassword);

        if (adminService == null) {
            System.out.println("adminService is null");
            model.addAttribute("passwordResetError", "Internal error. Please try again later.");
            return "DepartmentAdminResetPassword";
        }

        boolean resetSuccessful = adminService.adminChangePassword(email, oldPassword, newPassword, confirmPassword);
        if (resetSuccessful) {
            System.out.println("Password reset Successful: " + resetSuccessful);
            model.addAttribute("passwordResetMessage", "Password reset successful");
        } else {
            model.addAttribute("passwordResetError", "Failed to reset password. Please check your password");
        }

        return "DepartmentAdminResetPassword";
    }



    //department admin view a particular complaints details
    @GetMapping("/deptAdmin-view-particular-raise-complaints")  // action in href
    public String viewComplaints(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        DepartmentAdminDTO departmentAdminDto = (DepartmentAdminDTO) session.getAttribute("deptadmin");

        // Assuming findByUSerComplaintType requires a string like department name
        String departmentType = departmentAdminDto.getDepartmentType();  // Correct spelling of departmentType
        List<RaiseComplaintDTO> viewDepartmentComplaints = adminService.findByUSerComplaintType(departmentType);
        model.addAttribute("viewDepartmentComplaints", viewDepartmentComplaints);

        // Fetch only employees of the specific department
        List<EmployeeDTO> employees = adminService.getParticularEmployees(departmentType);  // Correct spelling of departmentType
        model.addAttribute("employees", employees);

        return "DepartmentAdminViewRaiseComplaints";
    }

    //allocate employee in complaint view
    @PostMapping("/allocate-employee")
    public String allocateEmployee(
            @RequestParam("complaintId") int complaintId,
            @RequestParam("employeeId") int employeeId,
            //@RequestParam("status") String status,
            Model model
    ) {
        try {
            System.out.println("Running allocate department");

            // Call the service method to allocate department
            adminService.allocateEmployee(complaintId, employeeId);
            System.out.println("complaintId" + complaintId);
            System.out.println("employeeId" + employeeId);
            model.addAttribute("successMessage", "Employee allocated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to allocate Employee. Please try again.");
            e.printStackTrace();
        }
        return "redirect:/deptAdmin-view-particular-raise-complaints";

    }




}



