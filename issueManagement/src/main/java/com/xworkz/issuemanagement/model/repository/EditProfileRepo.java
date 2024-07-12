package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface EditProfileRepo {
    default SignUpDTO findByEmail(String email)
    {
        return  null;
    }

    SignUpDTO updateSignupDtoByEmail(SignUpDTO updatedUserDetails);


}
