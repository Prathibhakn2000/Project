package com.xworkz.issuemanagement.dto;

import javax.persistence.*;

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


    @Override
    public String toString() {
        return "ImageUploadDTO{" +
                "imageId=" + imageId +
                ", signUpDTO=" + signUpDTO +
                ", imageName='" + imageName + '\'' +
                ", imageSize=" + imageSize +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
