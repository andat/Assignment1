package test;

import util.PasswordEncryptionUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EncryptionTest {
    private static Map<String, String> encryptedStrings;

    @org.junit.BeforeClass
    public static void setUp(){
        encryptedStrings = new HashMap<>();
        encryptedStrings.put("strawberry", "5E737F891DB1175442A39FDE73E51D781A545506D71C95477A6DEB5988BD7F9A");
        encryptedStrings.put("password", "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8");
        encryptedStrings.put("user", "04F8996DA763B7A969B1028EE3007569EAF3A635486DDAB211D512C85B9DF8FB");
        encryptedStrings.put("none", "140BEDBF9C3F6D56A9846D2BA7088798683F4DA0C248231336E6A05679E4FDFE");
    }

    @org.junit.Test
    public void testPasswordStrawberry(){
        String pass = PasswordEncryptionUtil.encryptPasswordSHA256("strawberry");
        assertEquals(encryptedStrings.get("strawberry").toLowerCase(), pass);
    }

    @org.junit.Test
    public void testPasswordPassword(){
        String pass = PasswordEncryptionUtil.encryptPasswordSHA256("password");
        assertEquals(encryptedStrings.get("password").toLowerCase(), pass);
    }

    @org.junit.Test
    public void testPasswordUser(){
        String pass = PasswordEncryptionUtil.encryptPasswordSHA256("user");
        assertEquals(encryptedStrings.get("user").toLowerCase(), pass);
    }

    @org.junit.Test
    public void testPasswordNone(){
        String pass = PasswordEncryptionUtil.encryptPasswordSHA256("none");
        assertEquals(encryptedStrings.get("none").toLowerCase(), pass);
    }
}
