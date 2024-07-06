package com.xworkz.issuemanagement.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public class PassWordGenerator {

    public  PassWordGenerator()
    {
        System.out.println("Created PasswordGenerator");
    }


    public static String generatePassword() {
        int length = 12;
        boolean useLetters = true;
        boolean useNumbers = true;
        // Generate a password that includes letters and numbers
        String password = RandomStringUtils.random(length, useLetters, useNumbers);

        // Optionally, include special characters by concatenating with a separate special characters set
        String specialChars = "!@#$%^&*()";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 3; i++) { // Ensure at least 3 special characters
            int position = random.nextInt(password.length());
            password = password.substring(0, position) + specialChars.charAt(random.nextInt(specialChars.length())) + password.substring(position + 1);
        }
        return password;
    }

    public static void main(String[] args) {
        System.out.println("Generated Password: " + generatePassword());
    }
}



