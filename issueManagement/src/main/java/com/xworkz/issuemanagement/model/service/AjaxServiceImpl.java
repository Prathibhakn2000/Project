package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.AjaxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AjaxServiceImpl implements AjaxService {


    @Autowired
    private AjaxRepo ajaxRepo;


    //Avoid duplicate emails and contact no using Ajax
    @Override
    public boolean existByEmail(String email) {
        System.out.println("Email:" + email);
        SignUpDTO dto = ajaxRepo.existByEmailId(email);
        if (dto != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean existByContactNumber(Long contactNumber) {
        {
            System.out.println("contactNumber:" + contactNumber);
            SignUpDTO dto = ajaxRepo.existByContactNumber(contactNumber);
            return dto != null;
        }
    }

}


