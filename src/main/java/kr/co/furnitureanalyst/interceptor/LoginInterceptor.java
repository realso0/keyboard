package kr.co.furnitureanalyst.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
	
		try {
			if(request.getSession().getAttribute("authUser")==null) {
				//response.sendRedirect("/furnitureanalyst/user/loginform");
			return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	/*@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("==============                    end           ==============");
		}
	}*/
	

}
