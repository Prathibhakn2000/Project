package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

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

    @PostMapping("/department-admins")
    public String registerDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO, Model model) {

        System.out.println("Running signInDepartmentAdmin method in AdminController...");
        boolean deptAdminDto = adminService.saveDepartmentAdmin(departmentAdminDTO);
        if (deptAdminDto) {
            model.addAttribute("signInsuccess", "Successfully logined" + departmentAdminDTO);
            return "DepartmentAdminSignIn";
        }
        model.addAttribute("signInFailed", "login failed" + departmentAdminDTO);
        return "AddDepartmentAdmins";
    }

    @PostMapping("/admin-sign-in")
    public String signInDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO,Model model)
    {
        //System.out.println("departPartAdmin"+departmentAdminDto);
        System.out.println("Running signInDepartmentAdmin method in AdminController...");
        DepartmentAdminDTO departmentAdminDTO1=adminService.findByDepartmentAdminEmailAndPassword(departmentAdminDTO.getEmail(),departmentAdminDTO.getPassword());
        if(departmentAdminDTO1!=null)
        {
            model.addAttribute("msg1","Successfully logined");
            return "DepartmentAdminSignIn";
        }
        model.addAttribute("msg1","login failed");
        return "DepartmentAdminSignIn";
    }
    }

