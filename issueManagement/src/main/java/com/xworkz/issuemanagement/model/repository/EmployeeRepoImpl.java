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
            manager.persist(employeeDTO);
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



//    @Override
//    public List<EmployeeDTO> fetchEmployeeNamesByDepartment(String departmentType) {
//        System.out.println("fetchEmployeeName method running in EmployeeRepoImpl...");
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//
////      String query = "SELECT e.employeeName FROM EmployeeDTO e WHERE e.departmentName = :departmentName";
//        String query = "SELECT e FROM EmployeeDTO e WHERE e.departmentType = :departmentType";
//
//
//        //  Query query1 = entityManager.createQuery("SELECT e FROM EmployeeDTO e where e.departmentName=:regDepartmentName AND e.status = 'ACTIVE'");
//
//        Query query1 = entityManager.createQuery(query);
//        query1.setParameter("departmentType", departmentType);
//
//        List<EmployeeDTO> fetchEmployeeNames = query1.getResultList();
//        System.out.println("ListOfEmployeeNames: {}" + fetchEmployeeNames);
//
//        return fetchEmployeeNames;
//    }
}

