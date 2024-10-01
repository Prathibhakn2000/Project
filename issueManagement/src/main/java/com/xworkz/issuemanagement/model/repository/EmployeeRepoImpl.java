package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean saveEmployeeDetails(EmployeeDTO employeeDTO) {
        System.out.println("Running saveEmployeeDetails method");

        // Set the status of the employee to 'active'
        employeeDTO.setStatus("active");

        System.out.println("Running save method in SignUpRepoImpl");
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            if (employeeDTO.getEmployeeId() > 0) {
                // If employee ID is present, update existing record
                manager.merge(employeeDTO);
            } else {
                // Otherwise, persist new record
                manager.persist(employeeDTO);
            }
            tx.commit();
            System.out.println("Employee saved with status: " + employeeDTO.getStatus());
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            manager.close();
        }
        return true;
    }


    //to check whether email exists or not in database
    @Override
    public EmployeeDTO findByEmail(String email) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            //System.out.println("Existing email:" +emailId);
            System.out.println("Existing email : " + email);
            entityTransaction.begin();
            String query = "SELECT e FROM EmployeeDTO e WHERE e.email =:email";

            Query query1 = entityManager.createQuery(query);
            query1.setParameter("email", email);
            EmployeeDTO employeeDTO = (EmployeeDTO) query1.getSingleResult();

            System.out.println("EmployeeDTO data :" + employeeDTO);
            entityTransaction.commit();

            return employeeDTO;


        } catch (NoResultException exception) {
            System.out.println("No entity found for email: " + email); // Log a warning instead of printing the stack trace

        } catch (PersistenceException persistenceException) {
            System.out.println("PersistenceException occurred while finding employee by email: " + email + persistenceException);
            entityTransaction.rollback(); // Rollback transaction in case of persistence exception

        } finally {
            System.out.println("findByEmail method closed");
            if (entityTransaction.isActive()) {
                entityTransaction.rollback(); // Ensure transaction is rolled back if still active
            }
            entityManager.close();
        }

        return null;
    }


//    @Override
//    public boolean updateEmployeeStatusToInActive(int employeeId,int complaintId) {
//
//        System.out.println("updateEmployeeStatusToInactive method  running in EmployeeRepoImpl");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        try {
//            entityTransaction.begin();
//            String jpqlUpdate = "UPDATE EmployeeDTO e SET e.status = 'inactive' WHERE e.employeeId = :employeeId";
//            Query updateResult = entityManager.createQuery(jpqlUpdate);
//            updateResult.setParameter("employeeId", employeeId);
//            int updatedData = updateResult.executeUpdate();
//            System.out.println("Updated Data: {}"+ updatedData);
//            // entityTransaction.commit();
//
//            // 2. Set employeeID to null in RaiseComplaintDTO
//            String jpqlUpdateComplaint = "UPDATE RaiseComplaintDTO r SET r.employeeId = null WHERE r.complaintId= :complaintId";
//            Query updateComplaint = entityManager.createQuery(jpqlUpdateComplaint);
//            updateComplaint.setParameter("complaintId", complaintId);
//            int updatedComplaints = updateComplaint.executeUpdate();
//            System.out.println("RaiseComplaint employee reference updated to null: {}"+ updatedComplaints);
//
//            // Commit the transaction after both operations
//            entityTransaction.commit();
//
//            return true;
//        } catch (PersistenceException persistenceException) {
//            if (entityTransaction.isActive()) {
//                entityTransaction.rollback(); // Rollback in case of failure
//            }
//            persistenceException.printStackTrace();
//        } finally {
//            entityManager.close();
//            System.out.println("Connection closed");
//        }
//
//        return false;
//    }
//}
//
//
//


    @Override
    public boolean updateEmployeeStatusToInActive(int employeeId, int complaintId) {
        System.out.println("updateEmployeeStatusToInactive method running in EmployeeRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            // Update employee status to inactive
            String jpqlUpdate = "UPDATE EmployeeDTO e SET e.status = 'inactive' WHERE e.employeeId = :employeeId";
            Query updateResult = entityManager.createQuery(jpqlUpdate);
            updateResult.setParameter("employeeId", employeeId);
            int updatedData = updateResult.executeUpdate();
            entityManager.flush(); // Ensure changes are written immediately
            System.out.println("Updated Data: " + updatedData);

            // Update RaiseComplaintDTO to remove employee reference
            String jpqlUpdateComplaint = "UPDATE RaiseComplaintDTO r SET r.employeeId = null WHERE r.complaintId = :complaintId";
            Query updateComplaint = entityManager.createQuery(jpqlUpdateComplaint);
            updateComplaint.setParameter("complaintId", complaintId);
            int updatedComplaints = updateComplaint.executeUpdate();
            entityManager.flush(); // Ensure changes are written immediately
            System.out.println("RaiseComplaint employee reference updated to null: " + updatedComplaints);

            entityTransaction.commit(); // Commit after all updates
            return true;
        } catch (PersistenceException persistenceException) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback(); // Rollback in case of failure
            }
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return false;
    }
}