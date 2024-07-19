package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.model.repository.RaiseComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaiseComplaintServiceImpl implements RaiseComplaintService{

    @Autowired
    private RaiseComplaintRepo raiseComplaintRepo;


    @Override
    public boolean saveRaiseComplaintData(RaiseComplaintDTO raiseComplaintDTO) {

        System.out.println("saveRaiseComplaintData method running RaiseComplaintServiceImpl");

            boolean data = raiseComplaintRepo.saveRaiseComplaintData(raiseComplaintDTO);
            if (data) {
                System.out.println("saveRaiseComplaintData  successful in RaiseComplaintServiceImpl..");
                return data;
            } else {
                System.out.println("saveRaiseComplaintData noy successful in RaiseComplaintServiceImpl..");
            }


            return true;
        }
    }

