package com.xworkz.issuemanagement.controller;//package com.xworkz.issuemanagement.controller;
//
//import com.xworkz.issuemanagement.dto.ImageUploadDTO;
//import com.xworkz.issuemanagement.dto.SignUpDTO;
//import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
//import com.xworkz.issuemanagement.model.service.EditProfileService;
//import com.xworkz.issuemanagement.model.service.ImageUploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Controller
//@RequestMapping("/")
//@SessionAttributes("signUpDTO")
//public class ImageUploadController {
//
//    public ImageUploadController()
//    {
//        System.out.println("Created ImageUploadController");
//    }
//
//    private static final String UPLOAD_DIR = "C:\\Users\\Prathibha Gowda\\OneDrive\\Desktop\\Image";
//
//    @Autowired
//    private EditProfileService editProfileService;
//
//    @Autowired
//    private ImageUploadService imageUploadService;
//
//    @Autowired
//    private HttpSession httpSession;
//
//    @Autowired
//    private ImageUploadRepo imageUploadRepo;
//
//    @PostMapping("/upload") //in this image also uploading
//    public String updateUserProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file") MultipartFile file, HttpSession httpSession) {
//        try {
//            if (file != null && !file.isEmpty()) {
//                String originalFilename = file.getOriginalFilename();
//                String newFileName = signUpDTO.getEmail() + "_" + originalFilename;
//                Path path = Paths.get(UPLOAD_DIR, newFileName);
//                //log.info("path: {}", path);
//                System.out.println("Path:"+path);
//                Files.write(path, file.getBytes());
//                //signUpDTO.setImageName(newFileName);
//
//
//
//                if (newFileName != null) {
//                    String imageUrl = "C:\\Users\\Prathibha Gowda\\OneDrive\\Desktop\\Image" + newFileName;
//                    httpSession.setAttribute("profileImage", imageUrl);
//                    model.addAttribute("imageURL", imageUrl);
//                }
//
//                // Save image details in database
//                ImageUploadDTO imageUploadDTO = new ImageUploadDTO();
//                imageUploadDTO.setSignUpDTO(signUpDTO); // Set the user
//                //imageUploadDto.setImagePath(newFileName); // Set the image path
//                imageUploadDTO.setImageName(originalFilename);
//                imageUploadDTO.setImageSize(file.getSize());
//                imageUploadDTO.setImageType(file.getContentType());
//                // editProfileImageDTO.setUser(user);
//                //imageUploadDto.setCreatedBy(String.valueOf(LocalDateTime.now()));
////                ditProfileImageDTO.setUser(signUpDTO); // Set the user
////                editProfileImageDTO.setImagePath(newFileName); // Set the image path
////                editProfileImageDTO.setImageName(originalFilename); // Set the original filename as imageName
//                //imageUploadService.saveImageDetails(editProfileImageDTO);
//                //imageUploadService.saveImageDetails(editProfileImageDTO); // Save image details
//                imageUploadRepo.saveProfileImage(imageUploadDTO);
//
//
//
//                // Store uploaded file name in session
//                httpSession.setAttribute("uploadedFileName", newFileName);
//            }
//
//            SignUpDTO updatedUserData = editProfileService.updateSignupDtoByEmail(signUpDTO);
//            if (updatedUserData != null) {
//                model.addAttribute("signUpDTO", updatedUserData);
//                model.addAttribute("msg", "Profile updated successfully");
//                httpSession.setAttribute("email", updatedUserData.getEmail());
//                httpSession.setAttribute("firstName", updatedUserData.getFirstName());
//                httpSession.setAttribute("lastName", updatedUserData.getLastName());
//                httpSession.setAttribute("contactNumber", updatedUserData.getContactNumber());
//
//
//
//                //display in console
//                //log.info("Image upload");
//                System.out.println("Image upload");
//                //log.info("file getName: {}", file.getName());
//                System.out.println("file getName:"+file.getName());
//                // log.info("file getSize: {}", file.getSize());
//                //log.info("file getContentType: {}", file.getContentType());
//                System.out.println("file getContentType:"+file.getContentType());
//                //log.info("file getResource: {}", file.getResource());
//                System.out.println("file getResources:"+file.getResource());
//                // log.info("file getBytes: {}", file.getBytes());
//                //log.info("file getOriginalFilename: {}", file.getOriginalFilename());
//                System.out.println("file getOriginalName:"+file.getOriginalFilename());
//                //log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());
//                System.out.println("file uploaded:"+file.getOriginalFilename()+"Content Type:"+file.getContentType());
//                return "Profile"; // Redirect to edit profile page
//            } else {
//                model.addAttribute("errorMessage", "Error updating profile");
//            }
//        } catch (IOException e) {
//            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
//            //log.error("Error uploading file", e);
//            System.out.println("Error uploading file"+e);
//        }
//
//
//
//        return "Profile"; // Handle error or success case
//    }
//}
//

import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
import com.xworkz.issuemanagement.model.service.EditProfileService;
import com.xworkz.issuemanagement.model.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
public class ImageUploadController {

    public ImageUploadController() {
        System.out.println("Created ImageUploadController");
    }

    private static final String UPLOAD_DIR = "C:\\Users\\Prathibha Gowda\\OneDrive\\Desktop\\Image";

    @Autowired
    private EditProfileService editProfileService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ImageUploadRepo imageUploadRepo;

    @PostMapping("/upload")
    public String updateUserProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file") MultipartFile file, HttpSession httpSession) {
        try {
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String newFileName = signUpDTO.getEmail() + "_" + originalFilename;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                System.out.println("Path:" + path);
                Files.write(path, file.getBytes());
                signUpDTO.setImageName(newFileName);

                if (newFileName != null) {
                    String imageUrl = UPLOAD_DIR + "\\" + newFileName;
                    httpSession.setAttribute("profileImage", imageUrl);
                    model.addAttribute("imageURL", imageUrl);
                }

                ImageUploadDTO imageUploadDTO = new ImageUploadDTO();
                imageUploadDTO.setSignUpDTO(signUpDTO);
                imageUploadDTO.setImageName(originalFilename);
                imageUploadDTO.setImageSize(file.getSize());
                imageUploadDTO.setImageType(file.getContentType());
                imageUploadService.saveOrUpdateImageDetails(imageUploadDTO);
            }

            SignUpDTO updatedUserData = editProfileService.updateSignupDtoByEmail(signUpDTO);
            if (updatedUserData != null) {
                model.addAttribute("signUpDTO", updatedUserData);
                model.addAttribute("msg", "Profile updated successfully");
                httpSession.setAttribute("email", updatedUserData.getEmail());
                httpSession.setAttribute("firstName", updatedUserData.getFirstName());
                httpSession.setAttribute("lastName", updatedUserData.getLastName());
                httpSession.setAttribute("contactNumber", updatedUserData.getContactNumber());

                System.out.println("Image upload");
                System.out.println("file getName:" + file.getName());
                System.out.println("file getContentType:" + file.getContentType());
                System.out.println("file getResource:" + file.getResource());
                System.out.println("file getOriginalName:" + file.getOriginalFilename());
                System.out.println("file uploaded:" + file.getOriginalFilename() + "Content Type:" + file.getContentType());
                return "Profile";
            } else {
                model.addAttribute("errorMessage", "Error updating profile");
            }
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            System.out.println("Error uploading file: " + e);
        }
        return "Profile";
    }
}
