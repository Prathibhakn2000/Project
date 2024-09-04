package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO) {
        System.out.println("Running saveEmployeeDetails method");

        System.out.println("Running save method in SignUpRepoImpl");
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();


        try {
            tx.begin();
            manager.persist(employeeDTO);
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



