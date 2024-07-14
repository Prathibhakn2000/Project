package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
public class SignUpRepoImpl implements SignUpRepo {

    public SignUpRepoImpl()
    {
        System.out.println("Creating SignUpRepoImpl");
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(SignUpDTO signUpDTO) {

        System.out.println("Running save method");

        System.out.println("Running save method in SignUpRepoImpl");
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

           manager.persist(signUpDTO);
            //manager.merge(signUpDTO);

            tx.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }
        return true;
    }


}
