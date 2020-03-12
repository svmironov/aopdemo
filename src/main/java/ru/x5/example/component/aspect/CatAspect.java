package ru.x5.example.component.aspect;

import java.util.List;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.x5.example.component.Bowl;
import ru.x5.example.component.Cat;
import ru.x5.example.model.Action;

//@Aspect
@Component
public class CatAspect {

    static final Logger logger = LogManager.getLogger(Cat.class);

    @Autowired
    private Bowl bowl;

    @Autowired
    private Cat cat;

    private Random random = new Random();
    private static final String supervisorName = "Тетя Глаша";
    private static final List<String> dreamsList = List.of("мышей", "еду", "игрушки");

    @Pointcut("execution(public * ru.x5.example.component.Cat.eat(..))")
    public void callCatEat() {
    }

    @Pointcut("execution(public * ru.x5.example.component.Cat.sleep(..))")
    public void callCatSleep() {
    }

    @Before("callCatEat()")
    public void beforeСallCatEat(JoinPoint jp) {
        logger.info(supervisorName + " кладет корм в миску кота");
        bowl.setFeed(new Object());
    }

    @Around("callCatSleep()")
    public Action aroundCallCatSleep(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        args[0] = dreamsList.get(random.nextInt(2));
        Action action = (Action) jp.proceed(args);
        action.setSecondAction(supervisorName + " не беспокоит кота");
        action.setResult(true);

        return action;
    }

    @AfterReturning(pointcut = "execution(public * ru.x5.example.component.Cat.sad(..))", returning = "returnValue")
    public void afterCallCatSad(JoinPoint jp, Action returnValue) {
        returnValue.setSecondAction(supervisorName + " играет с котом");
        returnValue.setResult(true);
    }

    @AfterThrowing(pointcut = "execution(public * ru.x5.example.component.Cat.sick(..))", throwing = "exception")
    public void afterCallCatSick(JoinPoint jp, Exception exception) {
        logger.info(exception.getMessage());
        logger.info(supervisorName + " вызывает ветеринаров");
        cat.incrementHappiness();
        cat.incrementHappiness();
    }

}
