
package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
public class EditProfileRepoImpl implements EditProfileRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public EditProfileRepoImpl() {
        System.out.println("Created EditProfileRepoImpl");
    }

    @Override
    public SignUpDTO findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            String query = "select s from SignUpDTO s where s.email = :email";
            SignUpDTO singleResult = entityManager.createQuery(query, SignUpDTO.class)
                    .setParameter("email", email)
                    .getSingleResult();
            transaction.commit();
            return singleResult;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public SignUpDTO updateSignupDtoByEmail(SignUpDTO updatedUserDetails) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            SignUpDTO existingUser = findByEmail(updatedUserDetails.getEmail());
            if (existingUser != null) {
                existingUser.setFirstName(updatedUserDetails.getFirstName());
                existingUser.setLastName(updatedUserDetails.getLastName());
                existingUser.setContactNumber(updatedUserDetails.getContactNumber());
                existingUser.setAlternativeContactNumber(updatedUserDetails.getAlternativeContactNumber());
                existingUser.setAddress(updatedUserDetails.getAddress());
                existingUser.setUpdatedBy(updatedUserDetails.getUpdatedBy());
                existingUser.setUpdatedOn(updatedUserDetails.getUpdatedOn());

                entityManager.merge(existingUser);
                entityTransaction.commit();
                return existingUser;
            }
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }
}
