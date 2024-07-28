package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.RaiseComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class RaiseComplaintServiceImpl implements RaiseComplaintService {

    @Autowired
    private RaiseComplaintRepo raiseComplaintRepo;


    public RaiseComplaintServiceImpl() {
        System.out.println("Created RaiseComplaintServiceImpl");

    }


    @Override
    public boolean saveRaiseComplaintType(RaiseComplaintDTO raiseComplaintDTO) {
        System.out.println("Running saveRaiseComplaintType method in RaiseComplaintServiceImpl ");
        boolean save = raiseComplaintRepo.saveRaiseComplaintType(raiseComplaintDTO);
        if (save) {
            System.out.println(" saved RaiseComplaint successfully ");
        } else {
            System.out.println(" Not saved RaiseComplaint successfully ");
        }
        return true;
    }


    @Override
    public Optional<RaiseComplaintDTO> findByUserId(int id) {
        return raiseComplaintRepo.findByUserId(id);
    }

    @Override
    public Optional<RaiseComplaintDTO> findBySignedInUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        SignUpDTO signedInUser = (SignUpDTO) httpSession.getAttribute("signUpDTO");
        if (signedInUser != null) {
            return raiseComplaintRepo.findByUserId(signedInUser.getId());
        }
        return Optional.empty();
    }


    @Override
    public List<RaiseComplaintDTO> getComplaintsByUserId(int userId) {
        return raiseComplaintRepo.findByRaiseComplaint(userId);
    }

    //edit
    @Override
    public RaiseComplaintDTO getComplaintById(int complaintId) {
        return raiseComplaintRepo.findByComplaintId(complaintId).orElse(null);
    }


    //update
//to set the signUpDTO id to stored id in
    @Override
    public List<RaiseComplaintDTO> updateRaiseComplaintUserDetails(RaiseComplaintDTO raiseComplaintDTO) {
        RaiseComplaintDTO complaintDTO = this.raiseComplaintRepo.findByComplaintId(raiseComplaintDTO.getComplaintId()).get();
        raiseComplaintDTO.setSignUpDTO(complaintDTO.getSignUpDTO());
        RaiseComplaintDTO raiseComplaintDTO1 = raiseComplaintRepo.updateRaiseComplaintUserDetails(raiseComplaintDTO);
        List<RaiseComplaintDTO> dtos = this.raiseComplaintRepo.findByRaiseComplaint(raiseComplaintDTO1.getSignUpDTO().getId());
        if (dtos != null) {
            System.out.println("update data successful");
            return dtos;
        } else {
            System.out.println("update not successful");
            return null;

        }


    }
}
