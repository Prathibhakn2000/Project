package com.xworkz.issuemanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;


// Sending password to email
@Configuration
public class EmailConfig {

        public EmailConfig()
        {
            System.out.println("Created EmailConfig");
        }

        @Bean
        public JavaMailSender javaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com.");
            mailSender.setPort(587);
            mailSender.setUsername("prathibhakn2000@gmail.com");
            mailSender.setPassword("ezsj fliq exxt dunz");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        }

    }


