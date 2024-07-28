package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface RaiseComplaintService {

    boolean saveRaiseComplaintType(RaiseComplaintDTO raiseComplaintDTO);

    //signup id fectch to complaint table
    Optional<RaiseComplaintDTO> findByUserId(int id);

    Optional<RaiseComplaintDTO> findBySignedInUser(HttpServletRequest request);

    //used for view raised complaint

    List<RaiseComplaintDTO> getComplaintsByUserId(int userId);

    //edit
    public RaiseComplaintDTO getComplaintById(int complaintId) ;

    //update

    List<RaiseComplaintDTO> updateRaiseComplaintUserDetails(RaiseComplaintDTO raiseComplaintDTO);



}
