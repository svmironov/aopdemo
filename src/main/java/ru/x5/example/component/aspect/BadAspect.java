package ru.x5.example.component.aspect;

import java.util.List;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.x5.example.component.Bowl;
import ru.x5.example.component.Cat;
import ru.x5.example.model.Action;

//@Aspect
@Component
public class BadAspect {

    @Pointcut("execution(public * ru.x5.example.component.Cat.sleep(..))")
    public void callCatSleep() {
    }

    @Around("callCatSleep()")
    public Action aroundCallCatSleep(ProceedingJoinPoint jp) throws Throwable {
       /// throw new RuntimeException();
       return new Action("Test");
    }
    
}
