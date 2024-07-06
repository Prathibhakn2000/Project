package com.xworkz.issuemanagement.model.service;

public interface AjaxService {

    //Avoid duplicate emails and contact no using Ajax
    boolean existByEmail(String email);

    boolean existByContactNumber(Long contactNumber);
}
