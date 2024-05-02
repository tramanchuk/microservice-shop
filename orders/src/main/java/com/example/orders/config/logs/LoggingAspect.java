package com.example.orders.config.logs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){}
//    @Before(value="executeLogging()")
//    public void logBeforeMethodCall(JoinPoint joinPoint){
//        StringBuilder message = logMethod(joinPoint);
//        LOGGER.info(message.toString());
//    }
//    @AfterReturning(value = "executeLogging()", returning =  "returnValue")
//    public void logAfterReturningMethodCall(JoinPoint joinPoint, Object returnValue){
//        StringBuilder message = logMethod(joinPoint);
//        logReturning(returnValue, message);
//        LOGGER.info(message.toString());
//    }
    @Around(value = "executeLogging()")
    public Object logAroundMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder message = logMethod(joinPoint);
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        message.append(" totalTime: ").append(totalTime).append("ms");
        logReturning(returnValue, message);
        LOGGER.info(message.toString());
        return returnValue;
    }
    private StringBuilder logMethod(JoinPoint joinPoint){
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0){
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg -> {
                message.append(arg).append(" | ");
            });
            message.append("]");
        }
        return message;
    }
    private void logReturning(Object returnValue, StringBuilder message){
        if (message == null) return;
        if (returnValue instanceof Collection){
            message.append(", returning: ")
                    .append(((Collection)returnValue).size())
                    .append(" instance(s)");
        }else{
            message.append(", returning: ").append(returnValue.toString());
        }
    }

}
