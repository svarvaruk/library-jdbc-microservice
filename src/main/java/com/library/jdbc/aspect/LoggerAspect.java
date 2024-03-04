package com.library.jdbc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	public static final Logger log =  LoggerFactory.getLogger(LoggerAspect.class);
	
	@Pointcut("execution(public * com.library.jdbc.service..*.*(..))")
	public void serviceMethods() {}
	  
	@Pointcut("execution(public * com.library.jdbc.dao..*.*(..))")
	public void repositoryMethods() {}	
	  
	@Pointcut("serviceMethods() || repositoryMethods()")
	public void logRequredMethods() {}	  
	
	@Around("logRequredMethods()")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		
		Object result = joinPoint.proceed();
		
		long elapsedTime = System.currentTimeMillis() - startTime;
		log.info("Method [{}] executed in {} ms", joinPoint.getSignature(), elapsedTime);
		return result;
	}
}