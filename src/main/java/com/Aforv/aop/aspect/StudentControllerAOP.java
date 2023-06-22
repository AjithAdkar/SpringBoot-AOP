package com.Aforv.aop.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.Aforv.aop.model.Student;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class StudentControllerAOP {
	@Pointcut("execution( * com.Aforv.aop.controller.*.*(..))")
	public void forControllerAllMethod() {
	}

	@Around(value = "forControllerAllMethod()")
	public Object beforeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.info(proceedingJoinPoint.getSignature().getName() + " Started");
		Object o = proceedingJoinPoint.proceed();
		log.info(proceedingJoinPoint.getSignature().getName() + " ended");
		return o;
	}

	@Before(value = "forControllerAllMethod()")
	public void beforeAdvice(JoinPoint joinPoint) {
		log.info("Before Aspect");
	}

	@After(value = "forControllerAllMethod()")
	public void afterAdvice(JoinPoint joinPoint) {
		log.info("After Aspect");
	}

	@AfterReturning(value = "forControllerAllMethod()")
	public void AfterReturningAdvice(JoinPoint joinPoint) {
		log.info("AfterReturning Aspect");
	}

	@AfterThrowing(value = "forControllerAllMethod()")
	public void AfterThrowingAdvice(JoinPoint joinPoint) {
		log.info("AfterThrowing Aspect");
	}
}
