package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
import com.xworkz.issuemanagement.model.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private ImageUploadRepo imageUploadRepo;

    public ImageUploadServiceImpl()
    {
        System.out.println("Created ImageUploadServiceImpl");
    }



    @Override
    public Optional<ImageUploadDTO> getImageDetailsByUserId(int id) {
        return imageUploadRepo.findByUserId(id);



    }


}