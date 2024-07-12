package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.EditProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class EditProfileServiceImpl implements EditProfileService {

    @Autowired
    private EditProfileRepo editProfileRepo;

    @Autowired
    private HttpSession httpSession;

    public EditProfileServiceImpl() {
        System.out.println("Created EditProfileServiceImpl");
    }

    @Override
    public SignUpDTO findByEmail(String email) {
        return editProfileRepo.findByEmail(email);
    }

    @Override
    public SignUpDTO updateSignupDtoByEmail(SignUpDTO signUpDTO) {

//        // Set audit fields on the existing user
//        String updatedBy = signUpDTO.getFirstName(); // or get the current user
//        LocalDateTime updatedOn = LocalDateTime.now();
//        setAudit(signUpDTO, updatedBy, updatedOn);


        SignUpDTO existingUser = editProfileRepo.findByEmail(signUpDTO.getEmail());
        if (existingUser != null) {
            httpSession.getAttribute("signUpDTO");
            // Set updated fields
            existingUser.setFirstName(signUpDTO.getFirstName());
            existingUser.setLastName(signUpDTO.getLastName());
            existingUser.setContactNumber(signUpDTO.getContactNumber());
            existingUser.setAlternativeContactNumber(signUpDTO.getAlternativeContactNumber());
            existingUser.setAddress(signUpDTO.getAddress());



            // Set audit fields on the existing user
            String updatedBy = httpSession.getAttribute("signedInUserEmail").toString();
            LocalDateTime updatedOn = LocalDateTime.now();
            setAudit(existingUser, updatedBy, updatedOn);


            return editProfileRepo.updateSignupDtoByEmail(existingUser);
        }
        return null;
    }

    @Override
    public void setAudit(SignUpDTO signUpDTO, String updatedBy, LocalDateTime updatedOn) {
        System.out.println("Running setAudit method....");
        signUpDTO.setUpdatedBy(updatedBy);
        signUpDTO.setUpdatedOn(updatedOn);
    }
}