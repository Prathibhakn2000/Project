package com.xworkz.issuemanagement.dto;

import javax.persistence.*;

@Entity
@Table(name="image_details")
public class ImageDTO {


    public ImageDTO()
    {
        System.out.println("Creating ImageDTO");

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id" )
    private int imageId;

    @Column(name = "image_name" )
    private String imageName;

    @Column(name = "image_size" )
    private Long imageSize;

    @Column(name = "image_type" )
    private String imageType;

    @Column(name = "id" )
    private int id;

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

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", imageSize=" + imageSize +
                ", imageType='" + imageType + '\'' +
                ", id=" + id +
                '}';
    }
}
