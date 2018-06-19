package com.ms.blogaopparametername.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.ms.blogaopparametername.User;

@Aspect
@Component
public class ValidatorAspect {
    /**
     * args 적용
     */
    @Pointcut("@annotation(com.ms.blogaopparametername.aop.AccountValidator)")
    public void accountValidator() {}

    /*@Before("accountValidator() && args(id,token,..)")
    public void validateAccount(Long id, String token) {
        System.out.println(id + " : " + token);
    }*/

    /**
     * joinPoint 적용
     * (maven 빌드에 추가되어 있긴 하지만 null이 반환 되면
     * javac -parameters 옵션 추가 후 Project rebuild 필요)
     */
    @Before("accountValidator()")
    public void validateAccount(JoinPoint joinPoint) {
        Long id = null;
        String token = null;
        String parameterName;
        Object[] parameterValues = joinPoint.getArgs();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        for (int i = 0; i < method.getParameters().length; i++) {
            parameterName = method.getParameters()[i].getName();
            if (parameterName.equals("id"))
                id = (Long) parameterValues[i];
            if (parameterName.equals("token"))
                token = (String) parameterValues[i];
        }

        System.out.println(id + " : " + token);
    }

    /**
     * 객체에 joinPoint 적용 (해당코드를 실행시킬 떄는
     * BlogAopParameterNameApplication에서 logic(), logic2(), logic3()은 주석 처리해주세요)
     */
    /*@Before("accountValidator()")
    public void validateAccount(JoinPoint joinPoint) {
        User user = Arrays.stream(joinPoint.getArgs())
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));
                .orElseGet(() -> {
                    System.out.println("User를 찾을 수 없습니다.");
                    return new User();
                });

        System.out.println(user.getId() + " : " + user.getToken());
    }*/
}
