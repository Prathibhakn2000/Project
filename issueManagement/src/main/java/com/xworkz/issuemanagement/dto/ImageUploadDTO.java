package com.xworkz.issuemanagement.dto;

import com.xworkz.issuemanagement.constant.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="image_details")
public class ImageUploadDTO {


    public ImageUploadDTO()
    {
        System.out.println("Creating ImageDTO");

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id" )
    private int imageId;

    @ManyToOne(fetch = FetchType.LAZY) // Example mapping assuming many images to one signup
    @JoinColumn(name = "id", referencedColumnName = "id") // Adjust as per your schema
    private SignUpDTO signUpDTO;


    @Column(name = "image_name" )
    private String imageName;

    @Column(name = "image_size" )
    private Long imageSize;

    @Column(name = "image_type" )
    private String imageType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" )
    private Status status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageType() {
        return imageType;
    }

    public SignUpDTO getSignUpDTO() {
        return signUpDTO;
    }

    public void setSignUpDTO(SignUpDTO signUpDTO) {
        this.signUpDTO = signUpDTO;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "ImageUploadDTO{" +
                "imageId=" + imageId +
                ", signUpDTO=" + signUpDTO +
                ", imageName='" + imageName + '\'' +
                ", imageSize=" + imageSize +
                ", imageType='" + imageType + '\'' +
                ", status=" + status +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                '}';
    }
}

