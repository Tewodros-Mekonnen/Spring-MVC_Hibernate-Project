package com.luv2code.com.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger

	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations...for controller
	@Pointcut("execution(* com.luve2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {

	}

	// pointcut declaration...for service
	@Pointcut("execution(* com.luve2code.springdemo.service.*.*(..))")
	private void forServicePackage() {

	}

	// pointcut declaration...for DAO
	@Pointcut("execution(* com.luve2code.springdemo.dao.*.*(..))")
	private void forDAOPackage() {

	}

	// this one is pointcut declaration for all three above
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {

	}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {

		// displaying method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("======>> in @Before: calling method: " + theMethod);

	}

	// add @AfterReturning advice

}
