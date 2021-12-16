package be.tirza.dinningreviewapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //    System.out.println(passwordEncoder.encode("Charlie-123"));
        System.out.println(passwordEncoder.encode("Admin-123"));
    }
}
