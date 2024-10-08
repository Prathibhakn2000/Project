package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class AdminRepoImpl implements AdminRepo {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public AdminDTO findByEmailAndPassword(String email, String password) {


        System.out.println("findByEmailAndPassword  method in AdminRepo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            String query = "SELECT a FROM AdminDTO a where a.email=:emailId AND a.password=:adminPassword";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("emailId", email);
            query1.setParameter("adminPassword", password);

            AdminDTO data = (AdminDTO) query1.getSingleResult();
            System.out.println("Data: " + data);
            entityTransaction.commit();
            return data;
        } catch (NoResultException e) {
            System.out.println("No result found");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Admin connection closed");
        }

        return null;
    }

    @Override
    public List<SignUpDTO> findById(SignUpDTO signUpDTO) {

        System.out.println("findById method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT d FROM  SignUpDTO d";
            Query query1 = entityManager.createQuery(query);
            List<SignUpDTO> data = query1.getResultList();
            System.out.println("Data:" + data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("Connection closed");
        }

        return Collections.emptyList();
    }

    //admin can view raise complaint details
    @Override
    public List<RaiseComplaintDTO> findById(RaiseComplaintDTO raiseComplaintDTO) {

        System.out.println("findById method in AdminRepoImpl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String query = "SELECT c FROM RaiseComplaintDTO c ORDER BY c.complaintId DESC";
            Query query1 = entityManager.createQuery(query);
            List<RaiseComplaintDTO> data = query1.getResultList();
            System.out.println("RaiseComplaintData:" + data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            System.out.println("connection closed");
        }

        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeOrCity method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT r FROM RaiseComplaintDTO r WHERE r.city=:City or r.complaintType=:ComplaintType ORDER BY r.complaintId DESC";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("City", city);
            query1.setParameter("ComplaintType", complaintType);


            List<RaiseComplaintDTO> raiseData = query1.getResultList();
            System.out.println("Result: " + raiseData);
            entityTransaction.commit();

            return raiseData;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            System.out.println("Connection closed");
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<RaiseComplaintDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchComplaintByTypeAndCity method running in AdminRepoImpl..");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            String query = "SELECT r FROM RaiseComplaintDTO r WHERE r.city=:City and r.complaintType=:ComplaintType ORDER BY r.complaintId DESC";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("City", city);
            query1.setParameter("ComplaintType", complaintType);

            List<RaiseComplaintDTO> raiseData = query1.getResultList();
            System.out.println("City:" + raiseData);
            entityTransaction.commit();

            return raiseData;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        } finally {
            System.out.println("Connection closed");
            entityManager.close();
        }
        return Collections.emptyList();
    }


    @Override
    public boolean saveDepartments(DepartmentDTO departmentDTO) {

        System.out.println("Running save method");

        System.out.println("Running save method in DepartmentRepoImpl");
        EntityManager manager = this.entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();


        try {
            tx.begin();
            manager.persist(departmentDTO);
            // manager.merge(departmentDTO);
            tx.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            tx.rollback();
        } finally {
            manager.close();
        }
        return true;
    }


    @Override
    public List<DepartmentDTO> getAllDepartments() {
        System.out.println("Running getAllDepartments method in admin repo implementation...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT d FROM DepartmentDTO d";
            Query query1 = entityManager.createQuery(query);
            List<DepartmentDTO> resultList = query1.getResultList();
            System.out.println("ResultList size: " + resultList.size());
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void allocateDepartment(int complaintId, int deptId, String status) {
        System.out.println("Running allocateDepartment method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            // Find the complaint
            RaiseComplaintDTO complaint = entityManager.find(RaiseComplaintDTO.class, complaintId);
            if (complaint == null) {
                throw new RuntimeException("Complaint not found for ID: " + complaintId);
            }
            System.out.println("Found complaint: " + complaint);

            // Find the department
            DepartmentDTO department = entityManager.find(DepartmentDTO.class, deptId);
            if (department == null) {
                throw new RuntimeException("Department not found for ID: " + deptId);
            }
            System.out.println("Found department: " + department);

            // Set the department for the complaint
            complaint.setDepartmentDTO(department);
            complaint.setStatus(status);

            // Merge the updated complaint
            complaint = entityManager.merge(complaint);
            System.out.println("Updated complaint after merge: " + complaint);

            entityTransaction.commit();
            System.out.println("Department allocated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    // save departments admin data
    @Override
    public boolean saveDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO) {
        System.out.println("Running saveDepartmentAdmin in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(departmentAdminDTO); // Use persist for new entities
            entityTransaction.commit();
            return true;
        } catch (PersistenceException e) {
            entityTransaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public DepartmentAdminDTO findByEmail(String email) {
        System.out.println("Running findByEmailId method...");
        if (entityManagerFactory == null) {
            System.out.println("entityManagerFactory is null");
            return null;
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        DepartmentAdminDTO departmentAdminDTO = null;
        try {
            Query query = entityManager.createQuery("SELECT s FROM DepartmentAdminDTO s WHERE s.email = :email");
            query.setParameter("email", email);
            departmentAdminDTO = (DepartmentAdminDTO) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user found with email: " + email);
        } catch (NonUniqueResultException e) {
            System.out.println("Multiple users found with email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return departmentAdminDTO;
    }


    @Override
    public DepartmentAdminDTO findByDepartmentAdminEmailAndPassword(String email, String password, String departmentType) {

        System.out.println("Running findByDepartmentAdminEmailAndPassword method...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("Select s from DepartmentAdminDTO s where s.email = :email and s.password = :password and s.departmentType =: departmentType");
            query.setParameter("email", email);
            query.setParameter("password", password);
            query.setParameter("departmentType", departmentType);
            return (DepartmentAdminDTO) query.getSingleResult();
        } catch (NoResultException e) {
            // Handle case where no result is found
            return null;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public boolean updateDeptAdminDetails(DepartmentAdminDTO departmentAdminDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.merge(departmentAdminDTO);
            tx.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public DepartmentDTO searchByDeptName(String departmentType) {
        System.out.println("Running searchByDeptType method");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT d FROM DepartmentDTO d WHERE d.departmentType = :departmentType";
            Query result = entityManager.createQuery(query);
            result.setParameter("departmentType", departmentType);
            DepartmentDTO departmentDTO = (DepartmentDTO) result.getSingleResult();
            return departmentDTO;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }


    @Override
    public List<DepartmentDTO> getAllDepts() {
        System.out.println("Running getAllDepartments method in Department admin repo implementation...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT d FROM DepartmentDTO d";
            Query query1 = entityManager.createQuery(query);
            List<DepartmentDTO> resultList = query1.getResultList();
            System.out.println("ResultList size: " + resultList.size());
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    //to view particulart department complaints by department admin

    @Override
    public List<RaiseComplaintDTO> findByUSerComplaintType(String complaintType) {
        System.out.println("findByUserComplaintType method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT r FROM RaiseComplaintDTO r where r.complaintType=:complaintType";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("complaintType", complaintType);
            List<RaiseComplaintDTO> data = query1.getResultList();
            System.out.println("DTO: " + data);
            System.out.println("Data size: " + data.size()); // Print the number of records fetched
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<EmployeeDTO> getParticularEmployees(String departmentType) {
        System.out.println("Running getAllEmployees method in admin repo implementation...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // Query to fetch employees by department type
            String query = "SELECT e FROM EmployeeDTO e WHERE e.departmentType = :departmentType AND e.status = 'active'";
            Query query1 = entityManager.createQuery(query);
            query1.setParameter("departmentType", departmentType);  // Bind departmentType parameter
            List<EmployeeDTO> resultList = query1.getResultList();
            System.out.println("ResultList size: " + resultList.size());
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }



    @Override
    public void allocateEmployee(int complaintId, int employeeId) {

        System.out.println("Running allocateEmployee method in AdminRepoImpl...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            // Find the complaint
            RaiseComplaintDTO complaint = entityManager.find(RaiseComplaintDTO.class, complaintId);
            if (complaint == null) {
                throw new RuntimeException("Complaint not found for ID: " + complaintId);
            }
            System.out.println("Found complaint: " + complaint);

            // Find the employee
            EmployeeDTO employee = entityManager.find(EmployeeDTO.class, employeeId);
            if (employee == null) {
                throw new RuntimeException("Department not found for ID: " + employeeId);
            }
//            employee.setStatus(status);
//            System.out.println("Found department: " + employee);

            // Set the employee for the complaint
            complaint.setEmployeeId(employee);


            // Merge the updated complaint
            complaint = entityManager.merge(complaint);
            System.out.println("Updated complaint after merge: " + complaint);

            entityTransaction.commit();
            System.out.println("Department allocated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }

    }



}


