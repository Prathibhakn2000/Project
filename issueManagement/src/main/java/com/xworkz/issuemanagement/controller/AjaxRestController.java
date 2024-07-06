package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AjaxRestController {

    @Autowired
    private AjaxService ajaxService;

    @GetMapping("/validateEmail/{email}")
    public String emailValidation(@PathVariable String email) {


        //this print statement is to print email in console
        System.out.println(email);

        //this is to check whether email is exits or not
        if (ajaxService.existByEmail(email)) {
            return "<span style='color:red;'>Email Already exists</span>";
        } else {
            return null;
        }
    }

    @GetMapping("/validateContactNumber/{contactNumber}")
        public String contactNumberValidation(@PathVariable Long contactNumber) {
            System.out.println(contactNumber);
            if (ajaxService.existByContactNumber(contactNumber)) {
                return "<span style='color:red;'>Contact Number Already exists</span>";
            } else {
                return null;
            }
        }
}

