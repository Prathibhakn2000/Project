package com.xworkz.issuemanagement.model.repository;//package com.xworkz.issuemanagement.model.repository;
//
//import com.xworkz.issuemanagement.dto.ImageUploadDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.TypedQuery;
//import java.util.Optional;
//
//@Repository
//public class ImageUploadRepoImpl implements ImageUploadRepo {
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    public ImageUploadRepoImpl()
//    {
//        System.out.println("Created ImageUploadRepoImpl");
//    }
//
//    @Override
//    @Transactional
//    public ImageUploadDTO saveProfileImage(ImageUploadDTO imageUploadDTO) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(imageUploadDTO);
//            entityManager.getTransaction().commit();
//            System.out.println("Saved profile image for user: " + imageUploadDTO );
//        } catch (Exception e) {
//            //log.error("Error saving profile image", e);
//            System.out.println("Error saving profile image"+e);
//            entityManager.getTransaction().rollback();
//        } finally {
//            entityManager.close();
//        }
//        return imageUploadDTO;
//    }
//
//    @Override
//    public Optional<ImageUploadDTO> findByUserId(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            TypedQuery<ImageUploadDTO> query = entityManager.createQuery(
//                    "SELECT i FROM ImageUploadDTO i WHERE i.id = :id", ImageUploadDTO.class);
//            query.setParameter("id", id);
//            return query.getResultList().stream().findFirst();
//        } finally {
//            entityManager.close();
//        }
//    }
//}

import com.xworkz.issuemanagement.dto.ImageUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class ImageUploadRepoImpl implements ImageUploadRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ImageUploadRepoImpl() {
        System.out.println("Created ImageUploadRepoImpl");
    }

    @Override
    @Transactional
    public ImageUploadDTO saveProfileImage(ImageUploadDTO imageUploadDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (imageUploadDTO.getImageId() == 0) {
                entityManager.persist(imageUploadDTO);
            } else {
                entityManager.merge(imageUploadDTO);
            }
            entityManager.getTransaction().commit();
            System.out.println("Saved profile image for user: " + imageUploadDTO );
        } catch (Exception e) {
            System.out.println("Error saving profile image: " + e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return imageUploadDTO;
    }

    @Override
    public Optional<ImageUploadDTO> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ImageUploadDTO> query = entityManager.createQuery(
                    "SELECT i FROM ImageUploadDTO i WHERE i.signUpDTO.id = :id", ImageUploadDTO.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }
}
