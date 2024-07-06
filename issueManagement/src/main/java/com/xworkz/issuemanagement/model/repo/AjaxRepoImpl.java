package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class AjaxRepoImpl implements AjaxRepo {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public SignUpDTO existByEmailId(String email) {
        System.out.println("Running findByEmailId");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query2 = entityManager.createQuery("select s from SignUpDTO s where s.email=:email ");
            query2.setParameter("email", email);
            System.out.println("Running existsByEmail method in SignUpRepoImpl");
            return (SignUpDTO) query2.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }


    @Override
    public SignUpDTO existByContactNumber(Long contactNumber) {
        System.out.println("Running findByContactNumber");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select s from SignUpDTO s where s.contactNumber=:contactNumber ");
            query.setParameter("contactNumber", contactNumber);
            System.out.println("Running existsByContactNumber method in SignUpRepoImpl");
            return (SignUpDTO) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;

    }
}


