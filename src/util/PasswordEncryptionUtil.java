package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionUtil {

    public static String encryptPasswordSHA256(String password) {
        String encryptedPass = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(password.getBytes());
            encryptedPass = bytesToHexa(digest);
            //encryptedPass = new String(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPass;
    }

    public static boolean validatePassword(String password, String storedPassword){
        return (storedPassword.equals(encryptPasswordSHA256(password)));
    }

    private static String bytesToHexa(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

//    public static void main(String args[]){
//        System.out.println(encryptPasswordSHA256("password"));
//        //System.out.println(validatePassword("password", "password"));
//    }
}

