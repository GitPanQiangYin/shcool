package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		String user_name = (String) session.getAttribute("user_name");
		String admin_name = (String) session.getAttribute("admin_name");
		if(url.equals("/shcool/login.action")||url.equals("/shcool/regist.action")||user_name!=null||admin_name!=null||url.equals("/shcool/user/login.action")||url.equals("/shcool/user/regist.action")||url.equals("/shcool/adminlogin.action")||url.equals("/shcool/admin/adminlogin.action")||url.equals("/shcool/addAdmin.action")||url.equals("/shcool/admin/addAdmin.action")
				||url.equals("/shcool/verification/imageServlet.action")||url.equals("/shcool/verification/verificationServlet.action")){
			return true;
		}else 
			response.sendRedirect(request.getContextPath()+"/login.action");
			return false;
		}
		
	

}
