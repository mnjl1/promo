package com.mmplus.promo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.mmplus.promo.controller.*.*(..))")
    public void forControllerPackage(){
    }

    @Pointcut("execution(* com.mmplus.promo.service.*.*(..))")
    public void forServicePackage(){
    }

    @Pointcut("forControllerPackage() || forServicePackage()")
    public void appFlow(){}

    @Before("appFlow()")
    public void before(JoinPoint joinPoint){
        String calledMethod = joinPoint.getSignature().toShortString();

        logger.info("========>>>>Before method: " +calledMethod);

        Object[] args = joinPoint.getArgs();

        for (Object arg: args
        ) {
            logger.info("=======>>>Called with argument: " +arg);
        }

    }

}
