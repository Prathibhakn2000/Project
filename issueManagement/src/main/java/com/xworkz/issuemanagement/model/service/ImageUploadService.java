package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ImageUploadDTO;

import java.util.Optional;

public interface ImageUploadService {

    Optional<ImageUploadDTO> getImageDetailsByUserId(int id);

    public ImageUploadDTO saveOrUpdateImageDetails(ImageUploadDTO imageUploadDTO);
}

