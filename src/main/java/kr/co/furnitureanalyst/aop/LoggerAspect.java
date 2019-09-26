package kr.co.furnitureanalyst.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

	protected Log log = LogFactory.getLog(LoggerAspect.class);
	//static String name = "";
	//static String type = "";
	
	@Around("execution(* kr.co.furnitureanalyst.dao.*Dao.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String type = joinPoint.getSignature().getDeclaringTypeName();
		//String name =null;
		long st = System.currentTimeMillis();
			
		/*if(type.indexOf("DAO") > -1) {
			name = "DAO \t\t: ";
		}*/
		try {

			return joinPoint.proceed();
		} finally {
			long et = System.currentTimeMillis();
			log.debug(type+"." + joinPoint.getSignature().getName()+ "() is finished");
			log.debug(" 경과시간:"+(et-st));

		}
		
		
	}
}
