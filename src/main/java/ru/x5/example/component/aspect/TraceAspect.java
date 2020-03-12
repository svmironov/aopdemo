package ru.x5.example.component.aspect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class TraceAspect {

    static final Logger logger = LogManager.getLogger(TraceAspect.class);

    @Before("execution(* ru.x5.example.component.*.*(..))")
    private void traceCall(JoinPoint jp){
        logger.info(jp.getSignature());
    }

}
