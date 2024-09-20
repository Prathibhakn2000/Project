package com.xworkz.issuemanagement.emailSending;

import com.xworkz.issuemanagement.dto.DepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSending {


    @Autowired
    private JavaMailSender mailSender;

    public MailSending() {
        System.out.println("creating mailSending");

    }
    public void sendSimpleEmail(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("Your Signin  Password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", You have been successfully Signed Up,\n\n" +
                "Please Sign in through this password: " + signUpDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender .send(message);
    }


    public void sendForgotPassword(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject(" Forgot Password ");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", Your Forgot Password is,\n\n" +
                "Please Sign in through this password: " + signUpDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender .send(message);
    }

    public void sendresetPassword(SignUpDTO signUpDTO,String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject(" Reset Password ");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", Your Reset Password is,\n\n" +
                "Please Sign in through this password: " + newPassword + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender .send(message);
    }

//
//    //Department admin mail sendings
//    public void sendDeptAdminPassword(DepartmentAdminDTO departmentAdminDTO) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(departmentAdminDTO.getEmail());
//        message.setSubject(" Department Admin SignIn Passoword ");
//        message.setText("Dear " + departmentAdminDTO.getFullName()+ ", Your SignIn Password is,\n\n" +
//                "Please Sign in through this password: " + departmentAdminDTO.getPassword()+"\n\n" +
//                "Thanks and Regards,\n" + " " +
//                "XworkzProject Team");
//        mailSender.send(message);
//    }
//
//    public void adminSendForgotPassword(DepartmentAdminDTO departmentAdminDTO) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(departmentAdminDTO.getEmail());
//        message.setSubject(" Forgot Password ");
//        message.setText("Dear " + departmentAdminDTO.getFullName() + " " + ", Your Forgot Password is,\n\n" +
//                "Please Sign in through this password: " + departmentAdminDTO.getPassword() + "\n\n" +
//                "Thanks and Regards,\n" + " " +
//                "XworkzProject Team");
//        mailSender .send(message);
//    }
//
//    public void adminSendresetPassword(DepartmentAdminDTO departmentAdminDTO,String newPassword) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(departmentAdminDTO.getEmail());
//        message.setSubject(" Reset Password ");
//        message.setText("Dear " + departmentAdminDTO.getFullName() +  ", Your Reset Password is,\n\n" +
//                "Please Sign in through this password: " + newPassword + "\n\n" +
//                "Thanks and Regards,\n" + " " +
//                "XworkzProject Team");
//        mailSender .send(message);
//    }



}




