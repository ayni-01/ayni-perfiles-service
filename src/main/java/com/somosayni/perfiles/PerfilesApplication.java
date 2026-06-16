package com.somosayni.perfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.somosayni.perfiles", "com.somosayni.shared"})
public class PerfilesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PerfilesApplication.class, args);
    }
}
