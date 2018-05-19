package com.example.Assignment2_LabApp.util;

import java.security.SecureRandom;

public class TokenGenerator {

    public static String generateToken(){
        SecureRandom random = new SecureRandom();;
        byte bytes[] = new byte[128];
        random.nextBytes(bytes);
        return bytes.toString();
    }
}
