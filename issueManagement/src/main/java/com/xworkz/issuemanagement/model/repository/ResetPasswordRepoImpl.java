package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class ResetPasswordRepoImpl implements ResetPasswordRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean emailExists(String email) {
        System.out.println("Running emailExists in ResetPasswordRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT e FROM SignUpDTO e WHERE e.email = :email";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            SignUpDTO signUpDTO = (SignUpDTO) query1.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean verifyOldPassword(String email, String oldPassword) {
        System.out.println("Running verifyOldPassword in ResetPasswordRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT e FROM SignUpDTO e WHERE e.email = :email AND e.password = :password";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            query1.setParameter("password", oldPassword);
            SignUpDTO signUpDTO = (SignUpDTO) query1.getSingleResult();
            System.out.println(signUpDTO);
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
         EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                String query = "UPDATE SignUpDTO e SET e.password = :password WHERE e.email = :email";
                Query query1 = entityManager.createQuery(query);
                query1.setParameter("password", newPassword);
                query1.setParameter("email", email);
                int executeData = query1.executeUpdate();
                System.out.println(executeData);
                entityTransaction.commit();
                return executeData > 0; // Return true if at least one record was updated
            } catch (Exception e) {
                e.printStackTrace();
                entityTransaction.rollback();
                return false;
            } finally {
                entityManager.close();
            }
        }

    }

