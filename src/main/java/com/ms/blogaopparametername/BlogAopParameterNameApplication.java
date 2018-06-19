package com.ms.blogaopparametername;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAopParameterNameApplication {
    private final BusinessService businessService;

    public BlogAopParameterNameApplication(BusinessService businessService) {
        this.businessService = businessService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogAopParameterNameApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (run) -> {
            this.businessService.logic(1L, "token");
            this.businessService.logic2("김민수", 1L, "token");
            this.businessService.logic3(999L, "token", 1L);
            this.businessService.logic4(new User(1L, "token"));
        };
    }
}
