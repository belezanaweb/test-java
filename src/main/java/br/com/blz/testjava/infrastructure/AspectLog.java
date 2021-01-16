package br.com.blz.testjava.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Configuration
public class AspectLog {

    private Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Pointcut("execution(* br.com.blz.testjava..*.*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object procedimentLog(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        logger.info(String.format("%s.%s args: {%s}", pjp.getTarget().getClass().getSimpleName(), method.getName(), Arrays.toString(pjp.getArgs())));

        Object output = pjp.proceed();

        logger.info(
            Objects.nonNull(output) ?
                String.format("End method, result :{%s}", output) :
                "End method"
        );

        return output;
    }
}
