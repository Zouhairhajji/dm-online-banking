package fr.esipe.creteil.utils;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author zouhairhajji
 */
@Aspect
@Component
public class AspectLog {

    static final Logger logger = Logger.getLogger(AspectLog.class);

    @Around("execution(* fr.esipe.creteil.controllers.UserController.*(..)) ")
    public Object logControllerMethods(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String httpMethod = request.getMethod();
        String path = request.getRequestURI();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String params = Arrays.toString(proceedingJoinPoint.getArgs());
        Object returnMethod = null;

        try {
            returnMethod = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }

        long duration = System.currentTimeMillis() - start;
        logger.info(String.format("[%d][%s][%s][%s][%s]%s", duration, className, httpMethod, path, methodName, params));

        return returnMethod;
    }

}
