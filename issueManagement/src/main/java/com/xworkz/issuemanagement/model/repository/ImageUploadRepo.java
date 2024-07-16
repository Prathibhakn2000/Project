package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ImageUploadRepo {

    void saveProfileImage(ImageUploadDTO imageUploadDTO);

    Optional<ImageUploadDTO> findByUserId(int id);



    @Transactional
    void setAllImagesInactiveForUser(int id);
}
