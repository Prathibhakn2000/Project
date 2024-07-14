package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.ImageUploadDTO;

import java.util.Optional;

public interface ImageUploadRepo {

    ImageUploadDTO saveProfileImage(ImageUploadDTO imageUploadDto);

    Optional<ImageUploadDTO> findByUserId(int id);
}
