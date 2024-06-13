package com.projetopipocagil.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.projetopipocagil.controles",
        "com.projetopipocagil.gestacao.service",
        "com.projetopipocagil.data",
        "com.projetopipocagil.gestacao.models",
        "com.projetopipocagil.seguranca",
        "com.projetopipocagil.dto",
        "com.projetopipocagil.login.security.config",
        "com.projetopipocagil.login.security.jwt",
        "com.projetopipocagil.response",
        "com.projetopipocagil.security.services",
        "com.projetopipocagil.request"
})
@EntityScan(basePackages = {"com.projetopipocagil.gestacao.models"})
@EnableJpaRepositories(basePackages = "com.projetopipocagil.data")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
    }
}
