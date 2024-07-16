package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.constant.Status;
import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.ImageUploadRepo;
import com.xworkz.issuemanagement.model.service.EditProfileService;
import com.xworkz.issuemanagement.model.service.ImageUploadService;
import com.xworkz.issuemanagement.model.service.SignUpService;
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
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
@SessionAttributes({"signUpDTO","imageUploadDTO"})
public class ImageUploadController {

    public ImageUploadController()
    {
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

//    @Autowired
//    private SignUpService signUpService;




    @PostMapping("/upload") //in this image also uploading
    public String updateUserProfile(SignUpDTO signUpDTO, Model model, @RequestParam("file") MultipartFile file, HttpSession httpSession) {
        try {
            //String newFileName=null;

            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String newFileName = signUpDTO.getEmail() + "_" + originalFilename;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                //log.info("path: {}", path);
                System.out.println("Path:"+path);
                Files.write(path, file.getBytes());
                signUpDTO.setImageName(newFileName);


                //here we have to call saveAndValidate method for saving imageName into signup table
               // signUpService.validateAndsave(signUpDTO);
                editProfileService.updateSignupDtoByEmail(signUpDTO);


                // Set all previous images inactive
                imageUploadRepo.setAllImagesInactiveForUser(signUpDTO.getId());

                if (newFileName != null) {
                    String imageUrl = "/images/" + newFileName;
                    httpSession.setAttribute("profileImage", imageUrl);
                    model.addAttribute("imageURL", imageUrl);



                }

                // Save image details in database
                ImageUploadDTO imageUploadDTO = new ImageUploadDTO();

                imageUploadDTO.setSignUpDTO(signUpDTO); // Set the user
                //imageUploadDto.setImagePath(newFileName); // Set the image path
                imageUploadDTO.setImageName(originalFilename);
                imageUploadDTO.setImageSize(file.getSize());
                imageUploadDTO.setImageType(file.getContentType());
                imageUploadDTO.setStatus(Status.ACTIVE);
                imageUploadDTO.setCreatedBy(signUpDTO.getFirstName());
                imageUploadDTO.setCreatedOn(LocalDateTime.now());
                imageUploadDTO.setUpdatedBy(signUpDTO.getFirstName());
                imageUploadDTO.setUpdatedOn(LocalDateTime.now());


                imageUploadRepo.saveProfileImage(imageUploadDTO);

                // Store uploaded file name in session
                httpSession.setAttribute("uploadedFileName", newFileName);
            }

            SignUpDTO updatedUserData = editProfileService.updateSignupDtoByEmail(signUpDTO);
            if (updatedUserData != null) {
                model.addAttribute("sinupDto", updatedUserData);
                model.addAttribute("msg", "Profile updated successfully");
                httpSession.setAttribute("email", updatedUserData.getEmail());
                httpSession.setAttribute("firstName", updatedUserData.getFirstName());
                httpSession.setAttribute("lastName", updatedUserData.getLastName());
                httpSession.setAttribute("contactNumber", updatedUserData.getContactNumber());



                //display in console
                //log.info("Image upload");
                System.out.println("Image upload");
                //log.info("file getName: {}", file.getName());
                System.out.println("file getName:"+file.getName());
                // log.info("file getSize: {}", file.getSize());
                //log.info("file getContentType: {}", file.getContentType());
                System.out.println("file getContentType:"+file.getContentType());
                //log.info("file getResource: {}", file.getResource());
                System.out.println("file getResources:"+file.getResource());
                // log.info("file getBytes: {}", file.getBytes());
                //log.info("file getOriginalFilename: {}", file.getOriginalFilename());
                System.out.println("file getOriginalName:"+file.getOriginalFilename());
                //log.info("File uploaded: {}, ContentType: {}", file.getOriginalFilename(), file.getContentType());
                System.out.println("file uploaded:"+file.getOriginalFilename()+"Content Type:"+file.getContentType());
                return "UserEditProfile"; // Redirect to edit profile page
            } else {
                model.addAttribute("errorMessage", "Error updating profile");
            }
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            //log.error("Error uploading file", e);
            System.out.println("Error uploading file"+e);
        }

        return "Profile"; // Handle error or success case
    }
}