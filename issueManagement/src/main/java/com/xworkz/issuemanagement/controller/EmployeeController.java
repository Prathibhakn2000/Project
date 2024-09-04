package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {


    public EmployeeController()
    {
        System.out.println("Creating EmployeeController");
    }

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AdminService adminService;


    @PostMapping("/employee-singup")
    public String signUp(@Valid EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes) {
        System.out.println("signUp method running in EmployeeController..");

        DepartmentDTO dataValid = this.adminService.searchByDeptName(employeeDTO.getDepartmentType());
        System.out.println("dataValid" +dataValid);
        employeeDTO.setDeptId(dataValid);

            boolean employeedetail=this.employeeService.employeeDetailsValidateAndSave(employeeDTO);
            if(employeedetail) {
                System.out.println("SignUpService registration successful in EmployeeController:" + employeeDTO);
                redirectAttributes.addFlashAttribute("message", "Register successful :" + employeeDTO.getEmpFullName());
            }
            else
            {
            System.out.println("SignUpService registration not successful in EmployeeController:"+employeeDTO);
                redirectAttributes.addFlashAttribute("message", "Not Register :" + employeeDTO.getEmpFullName());
            }

        return "redirect:/view-department-in-employee";

    }

    @GetMapping("/view-department-in-employee")
    public String getform(Model model){
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments",departments);
        return "EmployeeSignUp";
    }

}
