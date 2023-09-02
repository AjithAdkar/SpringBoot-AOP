package com.Aforv.aop.aspect;

import com.Aforv.aop.exceptionhandler.InvalidUserException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
@Slf4j
public class StudentControllerAOP {
//	@Pointcut("execution(<modifiers> <return_type> <full_class_name>.<method_name>(<parameter_types>))")
//	public void specificMethod() {}

    @Pointcut("execution(public  * com.Aforv.aop.controller.StudentController.findAllStudent())")
    public void findAllStudent() {
    }

    @Pointcut("execution(public  * com.Aforv.aop.controller.StudentController.updateStudent(..))")
    public void updateStudent() {
    }

    @Pointcut("execution( * com.Aforv.aop.controller.*.*(..))")
    public void forControllerAllMethod() {
    }

    @Around("@annotation(com.Aforv.aop.customannotation.PerformanceMonitor)")
    public Object beforeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info( proceedingJoinPoint.getSignature() + " executed in " + executionTime + "ms");

        return result;
    }

//
//	@Before(value = "forControllerAllMethod()")
//	public void beforeAdvice(JoinPoint joinPoint) {
//		System.out.println(joinPoint.getSignature());
//		log.info("Before Aspect");
//	}
//
//	@After(value = "forControllerAllMethod()")
//	public void afterAdvice(JoinPoint joinPoint) {
//		log.info("After Aspect");
//	}
//
//	@AfterReturning(value = "findAllStudent()")
//	public void AfterReturningAdvice(JoinPoint joinPoint) {
//		log.info("AfterReturning Aspect");
//	}

	@AfterThrowing(value = "forControllerAllMethod()")
	public void AfterThrowingAdvice(JoinPoint joinPoint) {
		log.info("AfterThrowing Aspect");
	}


    @Before(value = "updateStudent()")
    public void beforeAdviceAddSecurity(JoinPoint joinPoint) {
        //fetching the first parameter of method
        var role = joinPoint.getArgs()[0];

        if (!role.toString().equals("admin")) {
            log.warn(role + " try to update the user id " + joinPoint.getArgs()[0] + " detail on " + LocalTime.now());
            throw new InvalidUserException(role + " unable to edit the student detail contact Admin to update");
        }
        log.info(role + " updated the user id " + joinPoint.getArgs()[1] + " on  " + LocalTime.now());
    }
}
