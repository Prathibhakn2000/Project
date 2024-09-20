package com.xworkz.issuemanagement.emailSending;

import com.xworkz.issuemanagement.dto.DepartmentAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DepartmentAdminMailSending {

    @Autowired
    private JavaMailSender mailSender;

    public DepartmentAdminMailSending() {
        System.out.println("creating DepartmentAdminMailSending");
    }

        public void sendDeptAdminPassword(DepartmentAdminDTO departmentAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(departmentAdminDTO.getEmail());
        message.setSubject(" Department Admin SignIn Passoword ");
        message.setText("Dear " + departmentAdminDTO.getFullName()+ ", Your SignIn Password is,\n\n" +
                "Please Sign in through this password: " + departmentAdminDTO.getPassword()+"\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender.send(message);
    }

    public void adminSendForgotPassword(DepartmentAdminDTO departmentAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(departmentAdminDTO.getEmail());
        message.setSubject(" Forgot Password ");
        message.setText("Dear " + departmentAdminDTO.getFullName() + " " + ", Your Forgot Password is,\n\n" +
                "Please Sign in through this password: " + departmentAdminDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender .send(message);
    }

    public void adminSendresetPassword(DepartmentAdminDTO departmentAdminDTO,String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(departmentAdminDTO.getEmail());
        message.setSubject(" Reset Password ");
        message.setText("Dear " + departmentAdminDTO.getFullName() +  ", Your Reset Password is,\n\n" +
                "Please Sign in through this password: " + newPassword + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender .send(message);
    }


}
