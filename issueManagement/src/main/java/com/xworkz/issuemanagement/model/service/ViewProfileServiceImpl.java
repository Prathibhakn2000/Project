package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.ViewProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ViewProfileServiceImpl implements ViewProfileSerivce {

    @Autowired
    private ViewProfileRepo userProfileViewRepo;

    @Autowired
    private HttpSession httpSession;

    @Override
    public SignUpDTO getUserByEmail(String email) {
        return userProfileViewRepo.findByEmail(email);
    }

    @Override
    public String getLoggedInUserEmail() {
        return (String) httpSession.getAttribute("SignedInUserEmail");
    }
}


