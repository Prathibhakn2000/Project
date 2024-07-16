
package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.EditProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
@Transactional
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
        SignUpDTO existingUser = editProfileRepo.findByEmail(signUpDTO.getEmail());
        if (existingUser != null) {
            // Set updated fields
            existingUser.setFirstName(signUpDTO.getFirstName());
            existingUser.setLastName(signUpDTO.getLastName());
            existingUser.setContactNumber(signUpDTO.getContactNumber());
            existingUser.setAlternativeContactNumber(signUpDTO.getAlternativeContactNumber());
            existingUser.setAddress(signUpDTO.getAddress());
            existingUser.setImageName(signUpDTO.getImageName());

            // Set audit fields on the existing user
            String userEmail = (String) httpSession.getAttribute("signedInUserEmail");
            if (userEmail != null) {
                String updatedBy = signUpDTO.getFirstName();
                LocalDateTime updatedOn = LocalDateTime.now();
                setAudit(existingUser, updatedBy, updatedOn);

                return editProfileRepo.updateSignupDtoByEmail(existingUser);
            } else {
                // Handle the case when signedInUserEmail is not found in session
                System.err.println("Signed-in user email not found in session.");
                return null;
            }
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
