//package com.example.Assignment2_LabApp.config;
//
//import com.example.Assignment2_LabApp.controller.LoginController;
//import com.example.Assignment2_LabApp.model.login.Role;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        try {
//            Role role = LoginController.;
//
//            System.out.println("\n\nrole: " +role);
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
//            UsernamePasswordAuthenticationToken token =
//                    new UsernamePasswordAuthenticationToken(email, password, authorities);
//            return token;
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return false;
//    }
//}
