package de.admanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
