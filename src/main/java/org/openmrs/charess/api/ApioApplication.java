package org.openmrs.charess.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApioApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(ApioApplication.class, args);
        System.out.println("Started...");
    }

    @Override
    public void run(String... args) {
    }

}
