package com.xworkz.issuemanagement.model.service;//package com.xworkz.issuemanagement.model.service;
//
//import com.xworkz.issuemanagement.dto.ImageUploadDTO;
//import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//public class ImageUploadServiceImpl implements ImageUploadService {
//
//    @Autowired
//    private ImageUploadRepo imageUploadRepo;
//
//    public ImageUploadServiceImpl()
//    {
//        System.out.println("Created ImageUploadServiceImpl");
//    }
//
//    @Override
//    public Optional<ImageUploadDTO> getImageDetailsByUserId(int id) {
//        return imageUploadRepo.findByUserId(id);
//    }
//
//    @Override
//    @Transactional
//    public ImageUploadDTO saveOrUpdateImageDetails(ImageUploadDTO imageUploadDTO) {
//        // Find existing image by user ID
//        Optional<ImageUploadDTO> existingImageOpt = imageUploadRepo.findByUserId(imageUploadDTO.getSignUpDTO().getId());
//
//        if (existingImageOpt.isPresent()) {
//            ImageUploadDTO existingImage = existingImageOpt.get();
//            existingImage.setImageName(imageUploadDTO.getImageName());
//            existingImage.setImageSize(imageUploadDTO.getImageSize());
//            existingImage.setImageType(imageUploadDTO.getImageType());
//            // Update other fields as necessary
//
//            return imageUploadRepo.saveProfileImage(existingImage);
//        } else {
//            return imageUploadRepo.saveProfileImage(imageUploadDTO);
//        }
//    }
//}
//
//

import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private ImageUploadRepo imageUploadRepo;

    public ImageUploadServiceImpl() {
        System.out.println("Created ImageUploadServiceImpl");
    }

    @Override
    public Optional<ImageUploadDTO> getImageDetailsByUserId(int id) {
        return imageUploadRepo.findByUserId(id);
    }


    @Override
    @Transactional
    public ImageUploadDTO saveOrUpdateImageDetails(ImageUploadDTO imageUploadDTO) {
        Optional<ImageUploadDTO> existingImageOpt = imageUploadRepo.findByUserId(imageUploadDTO.getSignUpDTO().getId());

        if (existingImageOpt.isPresent()) {
            ImageUploadDTO existingImage = existingImageOpt.get();
            existingImage.setImageName(imageUploadDTO.getImageName());
            existingImage.setImageSize(imageUploadDTO.getImageSize());
            existingImage.setImageType(imageUploadDTO.getImageType());
            // Update other fields as necessary
            return imageUploadRepo.saveProfileImage(existingImage);
        } else {
            return imageUploadRepo.saveProfileImage(imageUploadDTO);
        }
    }
}

