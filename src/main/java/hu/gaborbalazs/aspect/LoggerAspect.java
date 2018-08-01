package hu.gaborbalazs.aspect;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerAspect {

	private static final String BEFORE = ">> ";
	private static final String AFTER = "<< ";
	private static final String BRACKET = "()";
	private static final String BRACKET_OPEN = "(";
	private static final String BRACKET_CLOSE = ")";
	
	@Pointcut("within(hu.gaborbalazs.web..*)")
	private void web() {
	}

	@Around("web()")
	public Object aroundRestOrDataMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.trace(BEFORE + joinPoint.getSignature().getName() + BRACKET_OPEN + Arrays.asList(joinPoint.getArgs()).stream().map(Object::toString).collect(Collectors.joining(",")) + BRACKET_CLOSE);
		Object retval = joinPoint.proceed();
		logger.trace(AFTER + joinPoint.getSignature().getName() + BRACKET);
		return retval;
	}
}
