package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;


@Repository
public class RaiseComplaintImpl implements  RaiseComplaintRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean saveRaiseComplaintData(RaiseComplaintDTO raiseComplaintDTO) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try {
                entityTransaction.begin();
                entityManager.persist(raiseComplaintDTO);
                entityTransaction.commit();
            } catch (PersistenceException e) {
                e.printStackTrace();
                System.out.println("not saved");
            } finally {
                entityManager.close();
                System.out.println("Connection closed");
            }
            return true;
        }
    }

//    @Override
//    public RaiseComplaintDTO findByUserId(int id) {
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        RaiseComplaintDTO result = null;
//
//        try {
//            entityTransaction.begin();
//
//            String query = "SELECT c FROM RaiseComplaintDTO c WHERE c.signUpDTO.id = :id";
//            Query query1 = entityManager.createQuery(query);
//            query1.setParameter("id", id);
//
//            result = (RaiseComplaintDTO) query1.getResultList().stream().findFirst().orElse(null);
//
//            entityTransaction.commit();
//        } catch (Exception e) {
//            if (entityTransaction.isActive()) {
//                entityTransaction.rollback();
//            }
//            log.error("Error finding complaint by user ID", e);
//        } finally {
//            entityManager.close();
//            log.info("Connection closed");
//        }
//
//        return result;
//    }
//}
