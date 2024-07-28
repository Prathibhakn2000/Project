package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("viewRaiseComplaint", viewData);
        if (viewData != null) {
            System.out.println("View raise complaint data successful in AdminController");
            return "AdminViewRaiseComplaintDetails";
        } else {
            System.out.println("View raise complaint data not successful in AdminController.");
        }
        return "AdminViewRaiseComplaintDetails";
    }

    //search by complaint  type

//    @PostMapping("search-complaint")
//    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//
//        System.out.println("searchByComplaintType method running  in   AdminController..");
//        List<RaiseComplaintDTO> data = adminService.searchByComplaintType(raiseComplaintDTO);
//        List<RaiseComplaintDTO> searchcity = adminService.searchByComplaintType(raiseComplaintDTO);
//        model.addAttribute("complaintType", data);
//
//        if (data != null) {
//            System.out.println("searchByComplaintType successful in AdminController..");
//            return "SearchRaiseComplaint";
//
//        } else {
//            System.out.println("searchByComplaintType not successful in AdminController.. ");
//        }
//
//        if (searchcity != null) {
//            System.out.println("searchByComplaintType successful in AdminController..");
//            return "SearchRaiseComplaint";
//
//        } else {
//            System.out.println("searchByComplaintType not successful in AdminController.. ");
//
//            return "SearchRaiseComplaint";
//        }
//    }


//    @PostMapping("search-complaint")
//    public String searchByComplaintType(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//        System.out.println("searchByComplaintType method running in AdminController..");
//
//        // Search by complaint type
//        List<RaiseComplaintDTO> complainttypedata = adminService.searchByComplaintType(raiseComplaintDTO);
//
//        // Search by city
//        List<RaiseComplaintDTO> citydata = adminService.searchComplaintByCity(raiseComplaintDTO);
//
//        String complaintType=raiseComplaintDTO.getComplaintType();
//        String city=raiseComplaintDTO.getCity();
//
//        //Search by complainttype and city
//         List<RaiseComplaintDTO> complainttypeandcitydata = adminService.searchComplaintByTypeAndCity(complaintType,city);
//
//        // Combine results
//        List<RaiseComplaintDTO> combinedData = new ArrayList<>();
//        if (complainttypedata != null && !complainttypedata.isEmpty()) {
//            combinedData.addAll(complainttypedata);
//        }
//        if (citydata != null && !citydata.isEmpty()) {
//            combinedData.addAll(citydata);
//        }
//
//            if (complaintType != null && !complaintType.isEmpty() && city != null && !city.isEmpty()) {
//               // List<RaiseComplaintDTO> complainttypeandcitydata = adminService.searchComplaintByTypeAndCity(complaintType, city);
//                if (complainttypeandcitydata != null && !complainttypeandcitydata.isEmpty()) {
//                    combinedData.addAll(complainttypeandcitydata);
//                }
//            }
//
//
//        // Add combined data to the model
//        model.addAttribute("complaintType", combinedData);
//
//        // Check if any data was found
//        if (!combinedData.isEmpty()) {
//            System.out.println("Data found for the given criteria in AdminController.");
//        } else {
//            System.out.println("No data found for the given criteria in AdminController.");
//        }
//
//
//        // Return the view
//        return "SearchRaiseComplaint";
//    }
//
//


//    @PostMapping("search-complaint")
//    public String searchByComplaintTypeOrCity(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//        System.out.println("searchByComplaintTypeOrCity method running in AdminController..");
//
//        String complaintType = raiseComplaintDTO.getComplaintType();
//        String city = raiseComplaintDTO.getCity();
//
//        // Perform the search
//        List<RaiseComplaintDTO> searchData = adminService.searchByComplaintTypeOrCity(complaintType, city);
//
//        List<RaiseComplaintDTO> searchData1 = adminService.searchByComplaintTypeAndCity(complaintType, city);
//
//        // Add data to the model
//        model.addAttribute("complaintData", searchData);
//
//        // Check if any data was found
//        if (!searchData.isEmpty()) {
//            System.out.println("Data found for the given criteria in AdminController.");
//        } else {
//            System.out.println("No data found for the given criteria in AdminController.");
//        }
//
//        // Return the view
//        return "SearchRaiseComplaint";
//    }
//}

//    @PostMapping("search-complaint")
//    public String searchByComplaintTypeOrCity(RaiseComplaintDTO raiseComplaintDTO, Model model) {
//        System.out.println("searchByComplaintTypeOrCity method running in AdminController..");
//
//        String complaintType = raiseComplaintDTO.getComplaintType();
//        String city = raiseComplaintDTO.getCity();
//
//        List<RaiseComplaintDTO> searchData = Collections.emptyList();
//
//        // Check which fields are provided and perform the appropriate search
//        if (complaintType != null && !complaintType.isEmpty() && city != null && !city.isEmpty()) {
//            // Both complaint type and city are provided
//            searchData = adminService.searchByComplaintTypeAndCity(complaintType, city);
//        } else if (complaintType != null && !complaintType.isEmpty()) {
//            // Only complaint type is provided
//            searchData = adminService.searchByComplaintTypeOrCity(complaintType, null);
//        } else if (city != null && !city.isEmpty()) {
//            // Only city is provided
//            searchData = adminService.searchByComplaintTypeOrCity(null, city);
//        }
//
//        // Add data to the model
//        model.addAttribute("complaintData", searchData);
//
//        // Check if any data was found
//        if (!searchData.isEmpty()) {
//            System.out.println("Data found for the given criteria in AdminController.");
//        } else {
//            System.out.println("No data found for the given criteria in AdminController.");
//        }
//
//        // Return the view
//        return "SearchRaiseComplaint";
//    }

    @PostMapping("search-complaint")
    public String searchUserComplaintDetails(RaiseComplaintDTO raiseComplaintDTO, Model model) {
        System.out.println("viewUserDetails method in AdminController..");

        List<RaiseComplaintDTO> listOfComplaintTypeAndCity = adminService.searchByComplaintTypeAndCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
        if (!listOfComplaintTypeAndCity.isEmpty()) {
            model.addAttribute("listOfComplaintType", listOfComplaintTypeAndCity);
            return "AdminViewRaiseComplaintDetails";
        }
        else {
            List<RaiseComplaintDTO> listOfComplaintTypeOrCity = adminService.searchByComplaintTypeOrCity(raiseComplaintDTO.getComplaintType(), raiseComplaintDTO.getCity());
            if (!listOfComplaintTypeOrCity.isEmpty()) {
                model.addAttribute("listOfComplaintType", listOfComplaintTypeOrCity);
                return "AdminViewRaiseComplaintDetails";
            }
        }

        return "AdminViewRaiseComplaintDetails";
    }


 //save depts
    @PostMapping("/add_department")
    public String signUp(@Valid DepartmentDTO departmentDTO, Model model) {
        System.out.println("department method running in department Controller..");
        System.out.println("DepartmentDTO ;" + departmentDTO);


        boolean dataValid = this.adminService.validateAndsave(departmentDTO);
        if(dataValid)
        {
            System.out.println("department registration successful in departmentController:"+departmentDTO);
            model.addAttribute("message", "Department Add Successfully ");
            return "redirect:/departments"; //getmapping action retuns

        }

        else
        {
            System.out.println("department registration not successful in departmentController:"+departmentDTO);
        }

        return "redirect:/departments"; //getmapping action retuns

    }

    //when data enter and refresh it save double time in database to avoid use this
    @GetMapping("departments")
    public String saveDept(DepartmentDTO departmentDTO,Model model)
    {
        model.addAttribute("message", "Department Add Successfully ");
        return "AddDepartments";
    }
}
