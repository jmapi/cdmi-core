package pw.cdmi.core.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pw.cdmi.exception.CaptchaException;

/************************************************************
 * 验证码的验证.<br/>
 * 
 * @author Roger
 * @version isoc Service Platform, 2015年12月8日
 ************************************************************/
public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
		if(!checkValidateCode(request)){
			throw new CaptchaException("验证码验证失败");
		}
		
		return super.attemptAuthentication(request, response);
	}
	
	/**
     * 验证码判断
     * @param request
     * @return
     */
    protected boolean checkValidateCode(HttpServletRequest request) {
      String result_verifyCode = request.getSession().getAttribute("checkCode").toString(); // 获取存于session的验证值
      String user_verifyCode = request.getParameter("checkCode");// 获取用户输入验证码
      logger.info("开始校验验证码，生成的验证码为："+result_verifyCode+" ，输入的验证码为："+user_verifyCode);
      if (null == user_verifyCode || !result_verifyCode.equalsIgnoreCase(user_verifyCode)) {
        return false;
      }
      return true;
    }

}
