package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public class RaiseComplaintRepoImpl implements  RaiseComplaintRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public RaiseComplaintRepoImpl() {
        System.out.println("Created RaiseComplaintRepoImpl");
    }


    @Override
    public boolean saveRaiseComplaintType(RaiseComplaintDTO raiseComplaintDTO) {
        System.out.println("Running saveRaiseComplaintType method in RaiseComplaintRepoImpl ");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(raiseComplaintDTO);
            entityTransaction.commit();
        } catch (PersistenceException persistenceException) {

            persistenceException.getStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return true;
    }


    @Override
    public Optional<RaiseComplaintDTO> findByUserId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
                    "SELECT i FROM RaiseComplaintDTO i WHERE i.s_id = :id", RaiseComplaintDTO.class);
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }


    //to view RaiseComplaint data
    @Override
    public List<RaiseComplaintDTO> findByRaiseComplaint(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
                    "SELECT r FROM RaiseComplaintDTO r WHERE r.signUpDTO.id = :userId ORDER BY r.complaintId DESC", RaiseComplaintDTO.class);
            query.setParameter("userId", id);
            List<RaiseComplaintDTO> results = query.getResultList();
            System.out.println("Found " + results.size() + " complaints for user ID " + id);
            return results;
        } finally {
            entityManager.close();
        }
    }

    @Override
        public Optional<RaiseComplaintDTO> findByComplaintId(int complaintId){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                TypedQuery<RaiseComplaintDTO> query = entityManager.createQuery(
                        "SELECT r FROM RaiseComplaintDTO r WHERE r.complaintId = :complaintId", RaiseComplaintDTO.class);
                query.setParameter("complaintId", complaintId);
                return query.getResultList().stream().findFirst();
            } finally {
                entityManager.close();
            }
        }

        @Override
        public RaiseComplaintDTO updateRaiseComplaintUserDetails(RaiseComplaintDTO raiseComplaintDTO) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try
            {
                entityTransaction.begin();
                entityManager.merge(raiseComplaintDTO);
                entityTransaction.commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                entityTransaction.rollback();
            }
            finally {
                entityManager.close();
                System.out.println("updateRaiseComplaintUserDetails connection closed");
            }

            return raiseComplaintDTO;
        }



}