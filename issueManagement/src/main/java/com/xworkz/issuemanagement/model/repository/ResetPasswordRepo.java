package com.xworkz.issuemanagement.model.repository;

public interface ResetPasswordRepo {

    boolean emailExists(String email);

    boolean verifyOldPassword(String email, String oldPassword);

    boolean updatePassword(String email, String newPassword);
}
