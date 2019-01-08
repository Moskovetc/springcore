package org.shop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* *.initUsers(..))")
    private void initUsersMethod() {
    }

    @Pointcut("execution(* *.setUsername(..))")
    private void setUsernameMethod() {
    }

    @Around("initUsersMethod()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("Before invoking getName() method");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("After invoking getName() method. Return value="+value);
        return value;
    }


//    @Before("allLogEventmethods()")
//    public void logBefore(JoinPoint joinPoint) {
//        logger.info("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName()
//                + " " + joinPoint.getSignature().getName());
//    }

}
