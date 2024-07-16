package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.constant.Status;
import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Optional;

@Repository
public class ImageUploadRepoImpl implements ImageUploadRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ImageUploadRepoImpl()
    {
        System.out.println("Created ImageUploadRepoImpl");
    }

    @Override
    @Transactional
    public void saveProfileImage(ImageUploadDTO imageUploadDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(imageUploadDTO);
            entityManager.getTransaction().commit();
            System.out.println("Saved profile image for user: " + imageUploadDTO );
        } catch (Exception e) {
            //log.error("Error saving profile image", e);
            System.out.println("Error saving profile image"+e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<ImageUploadDTO> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ImageUploadDTO> query = entityManager.createQuery(
                    "SELECT i FROM ImageUploadDTO i WHERE i.id = :id", ImageUploadDTO.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }

//    public ImageUploadDto updateProfileImageById(int id)
//    {
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//        try {
//            ImageUploadDto query=entityManager.createQuery("Select i from ImageUploadDto where i.id=:id  ")
//        }
//    }

    @Override
    @Transactional
    public void setAllImagesInactiveForUser(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            String query = "UPDATE ImageUploadDTO i SET i.status = :status WHERE i.signUpDTO.id = :userId";
            Query updateQuery = entityManager.createQuery(query);
            updateQuery.setParameter("status", Status.INACTIVE);
            updateQuery.setParameter("userId", id);
            int updatedCount = updateQuery.executeUpdate();

            System.out.println("Number of images set inactive: " + updatedCount);

            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println("Error setting images inactive for user with ID " + id + ": " + e.getMessage());
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
}



