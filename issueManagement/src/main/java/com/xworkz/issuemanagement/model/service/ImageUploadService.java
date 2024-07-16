package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ImageUploadService {

    Optional<ImageUploadDTO> getImageDetailsByUserId(int id);

}

