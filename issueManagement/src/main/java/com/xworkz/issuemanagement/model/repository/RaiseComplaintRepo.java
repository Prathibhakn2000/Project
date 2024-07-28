package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;

import java.util.List;
import java.util.Optional;

public interface RaiseComplaintRepo {

    boolean saveRaiseComplaintType(RaiseComplaintDTO raiseComplaintDTO);

    Optional<RaiseComplaintDTO> findByUserId(int id);

    //used for view raise complaint

    List<RaiseComplaintDTO> findByRaiseComplaint(int id);

    //edit
     Optional<RaiseComplaintDTO> findByComplaintId(int complaintId) ;

    //update
    RaiseComplaintDTO updateRaiseComplaintUserDetails(RaiseComplaintDTO raiseComplaintDTO);



}
