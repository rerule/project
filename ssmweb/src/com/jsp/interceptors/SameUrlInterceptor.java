package com.jsp.interceptors;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SameUrlInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LogManager.getLogger(SameUrlInterceptor.class.getName());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			SameUrlToken sameUrl= method.getAnnotation(SameUrlToken.class);
			if (sameUrl !=null) {
				if (repeatDataValidator(request)) {
					return false;
				}else {
					return true;
				}
				
			}else {
				return true;
			}
		}else {
			return super.preHandle(request, response, handler);
		}
		
	}

	private boolean repeatDataValidator(HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String params = objectMapper.writeValueAsString(request.getParameterMap());
		String url = request.getRequestURI();
		Map<String, String> map = new HashMap<String, String>();
		map.put(url, params);
		String nowUrlPrams = map.toString();
		logger.info(nowUrlPrams);
		Object preUrlParams = request.getSession().getAttribute("urlPrams");
		if (preUrlParams == null) {
			request.getSession().setAttribute("urlPrams", nowUrlPrams);
			return false;
		}else {
			if (preUrlParams.toString().equals(nowUrlPrams)) {
				logger.info("相同请求被拦截了");
				return true;
			}else {
				request.getSession().setAttribute("urlParams", nowUrlPrams);
				return false;
			}
		}
		
		
	}

}
