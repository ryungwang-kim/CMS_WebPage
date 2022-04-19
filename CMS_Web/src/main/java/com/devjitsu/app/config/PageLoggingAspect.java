package com.devjitsu.app.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PageLoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); // PointCut : 적용할 지점 또는 범위 선택 
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)") 
	private void publicTarget() { } 
	
	// Advice : 실제 부가기능 구현부 
	@Around("publicTarget()") 
	public Object calcPerformanceAdvice(ProceedingJoinPoint joinPoint) throws Throwable { 
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

	 	logger.info("This - '" + joinPoint.getThis() + "'"); // Advice를 행하는 객체
        logger.info("Kind - '" + joinPoint.getKind() + "'"); // 해당 Advice 의 타입
        logger.info("Target - '" + joinPoint.getTarget() + "'"); // Target 객체

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();
        // getSignature() : 실행되는 대상 객체의 메서드에 대한 정보를 가지고 옴

        if (name.contains("Controller")) {
            type = "Controller - '";

        } else if (name.contains("Service")) {
            type = "Service - '";
        }

        logger.info(type + name + "." + joinPoint.getSignature().getName() + "()'");
				// getName - 메서드 이름
        
		// 접근 경로(메뉴 패턴)

		String requestUri = request.getRequestURI();

		logger.info("requestUrl  : " + request.getQueryString());
		logger.info("requestMethod : " + request.getMethod());
		
		String contextPath = request.getContextPath();

		String pattern = requestUri.replaceAll("(^" + contextPath + ")|((\\.[^\\.]*)$)|((/[^/]+){1}/*$)", "")

				+ "/";

		String operateFile = requestUri.replace(contextPath, "").replace(pattern, "");

		String operateType = "insert";

		if (operateFile.startsWith("update")) {

			operateType = "update";

		} else if (operateFile.startsWith("delete")) {

			operateType = "delete";

		}

		logger.debug("Operate Type : " + operateType);


        return joinPoint.proceed();
	}

}
