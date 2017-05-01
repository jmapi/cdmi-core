package pw.cdmi.utils;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * 表单数据提取工具类
 * 
 * @author 伍伟
 * @version v1.0
 */
public class RequestUtils {

	/**
	 * 提取字段符,前对字段串进行去除空格处理
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 * @return String 
	 */
	public static String getString(HttpServletRequest request,String parameterName)
	{
		if(request==null || StringUtils.isEmpty(parameterName))
			return null;
			
		String value = request.getParameter(parameterName);
		if(value!=null)
			value = value.trim();
		
		return value;
	}
	
	/**
	 * 提取字段符,前对字段串进行去除空格处理，如果表单字符串为空
	 * 则返指定的默认值。
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 * @param defaultValue 默认值
	 * @return String 
	 */
	public static String getString(HttpServletRequest request,String parameterName,String defaultValue)
	{
		String value = RequestUtils.getString(request,parameterName);
		if(StringUtils.isEmpty(value))
			value = defaultValue;
		
		return value;
	}

	/**
	 * 提取数值,如果不存则，则返回0
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 * @return int 
	 */
	public static short getShort(HttpServletRequest request,String parameterName)
	{
		if(request==null)
			return 0;
		
		short value = 0;
		String strValue = RequestUtils.getString(request,parameterName);
		if(StringUtils.isEmpty(strValue))
			return 0;
			
		try
		{
			value = new Short(strValue).shortValue();
		}
		catch(Exception ex)
		{
			value = 0;
		}
			
		return value;
	}
	
	/**
	 * 提取数值,如果不存则，则返回0
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 * @return int 
	 */
	public static int getInt(HttpServletRequest request,String parameterName)
	{
		if(request==null)
			return 0;
		
		int value = 0;
		String strValue = RequestUtils.getString(request,parameterName);
		if(StringUtils.isEmpty(strValue))
			return 0;
			
		try
		{
			value = new Integer(strValue).intValue();
		}
		catch(Exception ex)
		{
			value = 0;
		}
			
		return value;
	}

    /**
     * 提取数值,如果不存则，则返回0
     * @param request HttpServletRequest
     * @param parameterName 参数名
     * @return int 
     */
    public static long getLong(HttpServletRequest request,String parameterName)
    {
        if(request==null)
            return 0;
        
        long value = 0;
        String strValue = RequestUtils.getString(request,parameterName);
        if(StringUtils.isEmpty(strValue))
            return 0;
            
        try
        {
            value = new Long(strValue).longValue();
        }
        catch(Exception ex)
        {
            value = 0;
        }
            
        return value;
    }
	/**
	 * 提取数值,如果不存则，则返回默认值
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 * @param defaultValue 默认值
	 * @return short 
	 */
	public static short getShort(HttpServletRequest request,String parameterName,short defaultValue)
	{
		short value = RequestUtils.getShort(request,parameterName);
		if(value==0)
			value = defaultValue;
		
		return value;
	}

	/**
	 * 提取数值,如果不存则，则返回默认值
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 * @param defaultValue 默认值
	 * @return int 
	 */
	public static int getInt(HttpServletRequest request,String parameterName,int defaultValue)
	{
		int value = RequestUtils.getInt(request,parameterName);
		if(value==0)
			value = defaultValue;
		
		return value;
	}
	
    /**
     * 提取数值,如果不存则，则返回默认值
     * @param request HttpServletRequest
     * @param parameterName 参数名
     * @param defaultValue 默认值
     * @return int 
     */
    public static long getLong(HttpServletRequest request,String parameterName,int defaultValue)
    {
        long value = RequestUtils.getLong(request,parameterName);
       
        if(value==0)
            value = defaultValue;
        
        return value;
    }    

	/**
	 * 提取参数数组
	 * @param request HttpServletRequest
	 * @param parameterName 参数名
	 */
	public static String[] getParameterValues(HttpServletRequest request,String parameterName)
	{
		if(StringUtils.isEmpty(parameterName))
			return null;
			
		return request.getParameterValues(parameterName);
	}
	
	/**
	 * 提取所有参数及其值,并以隐藏控件的方式打出
	 */
	public static String printAllParameterInHidden(HttpServletRequest request)
	{
		StringBuffer html 	= new StringBuffer();
		String hidden		= "";
		
		Map paramMap = request.getParameterMap();
		Enumeration eName	= request.getParameterNames();
		if(eName!=null && paramMap!=null) 
		{
			while(eName.hasMoreElements())
			{
				String name = (String) eName.nextElement();
				String[] values = (String[])paramMap.get(name);
				for(int i=0;i<values.length;i++)
				{
					html.append("<input type=\"hidden\" name=\""+name+"\" value=\""+values[i]+"\">\n");
				}
			}
			
			hidden = html.toString();
		}
		
		return hidden;		
	}
	
	/**
	 * 提取所有参数,并以URL的方式出现
	 */
	public static String getParameterForURL(HttpServletRequest request)
	{
		StringBuffer buf = new StringBuffer();

		Map paramMap = request.getParameterMap();
		Enumeration eName	= request.getParameterNames();
		if(eName!=null && paramMap!=null) 
		{
			while(eName.hasMoreElements())
			{
				String name = (String) eName.nextElement();
				String[] values = (String[])paramMap.get(name);
				for(int i=0;i<values.length;i++)
				{
					buf.append("&");
					buf.append(name + "=" + values[i]);
				}
			}
		}

		return buf.toString();
	}
	
	/**
	 * 获得请求的来源IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddress(HttpServletRequest request){    
        String ip = request.getHeader("x-forwarded-for");    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("WL-Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_CLIENT_IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getRemoteAddr();    
        }    
        return ip;    
    } 
}
