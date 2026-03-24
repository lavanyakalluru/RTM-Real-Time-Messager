package com.example.serverregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServerRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRegisterApplication.class, args);
    }

}
