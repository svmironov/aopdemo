package ru.x5.example.component.aspect;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.x5.example.model.Action;

//@Aspect
@Component
public class CacheAspect {

    static final Logger logger = LogManager.getLogger(CacheAspect.class);

    private Cache<Object, Action> cache = CacheBuilder.newBuilder().build();

    @Around("@annotation(ru.x5.example.annotation.Cache)")
    public Action cachedCall(ProceedingJoinPoint jp) throws Throwable {
        Object arg = jp.getArgs()[0];

        Action action = null;
        if(arg != null){
            action = cache.getIfPresent(arg);
            if(action != null){
                logger.info("Value loaded from cache: " + action);
                return action;
            }
            action = (Action) jp.proceed();
            cache.put(arg, action);
        }
        
        return action;
    }
}
