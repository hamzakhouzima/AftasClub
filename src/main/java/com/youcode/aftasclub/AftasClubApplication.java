package com.youcode.aftasclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"com.youcode.aftasclub", "com.youcode.aftasclub.ToolKit"}) // Include your package and the toolkit package

public class AftasClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(AftasClubApplication.class, args);
    }

}
