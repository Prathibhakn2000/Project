package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ImageUploadRepo {

    boolean uploadImage(SignUpDTO signUpDTO);
}
