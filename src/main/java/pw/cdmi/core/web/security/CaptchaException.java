package pw.cdmi.core.web.security;

import org.springframework.security.core.AuthenticationException;

/************************************************************
 * 验证码验证异常.<br/>
 * TODO(对类的作用含义说明 – 可选).<br/>
 * TODO(对类的使用方法说明 – 可选).<br/>
 * 
 * @author Roger
 * @version aws Service Platform, 2015年12月8日
 ************************************************************/
public class CaptchaException extends AuthenticationException{

	public CaptchaException(String msg, Throwable t) {
		super(msg, t);
	}

	/** serialVersionUID:异常序列化ID.*/
	private static final long serialVersionUID = 1L;

	public CaptchaException(String msg) {
		super(msg);
	}

}
