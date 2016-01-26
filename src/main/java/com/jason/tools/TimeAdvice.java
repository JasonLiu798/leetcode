package com.jason.tools;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by JasonLiu on 2015/8/12.
 */
public class TimeAdvice {

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		long t1 = System.currentTimeMillis();
//		Object[] args = pjp.getArgs();
//		Object target = pjp.getTarget();
		Object result = pjp.proceed();
		System.out.println(method+ " cost "+(System.currentTimeMillis()-t1)+" ms");
		return  result;
	}
}
