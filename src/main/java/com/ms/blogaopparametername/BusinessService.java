package com.ms.blogaopparametername;

import org.springframework.stereotype.Service;

import com.ms.blogaopparametername.aop.AccountValidator;

@Service
public class BusinessService {
    @AccountValidator
    public void logic(Long id, String token) {
        System.out.println("Hello World\n");
    }

    @AccountValidator
    public void logic2(String name, Long id, String token) {
        System.out.println("Goodbye World\n");
    }

    @AccountValidator
    public void logic3(Long businessId, String token, Long id) {
        System.out.println("Good Morning World\n");
    }

    @AccountValidator
    public void logic4(User user) {
        System.out.println("Good Evening\n");
    }
}
