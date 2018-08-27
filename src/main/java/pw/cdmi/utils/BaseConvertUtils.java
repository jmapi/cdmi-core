/*
 * Copyright Notice:
 *      Copyright  1998-2009, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package pw.cdmi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BaseConvertUtils extends ConvertUtils
{
    private BaseConvertUtils()
    {
    }
    
    static
    {
        register(new HexToLongConvert(), HexLongType.class);
        register(new BlobToStringConvert(), BlobStringType.class);
        register(new StreamToStringConvert(), StreamStringType.class);
        register(new ClassNameToInstanceConvert(), ClassNameObjectType.class);
    }
    
    /**
     * 将对象安全转换成整数。
     */
    public static Integer toInt(Object arg, Integer defaultValue)
    {
        if (null == arg)
        {
            return defaultValue;
        }
        
        Object value = convert(arg, Integer.class);
        if (null == value)
        {
            return defaultValue;
        }
        return (Integer) value;
    }
    
    /**
     * 将对象安全转换成长整型。
     */
    public static Long toLong(Object arg, Long defaultValue)
    {
        if (null == arg)
        {
            return defaultValue;
        }
        
        Object value = convert(arg, Long.class);
        if (null == value)
        {
            return defaultValue;
        }
        return (Long) value;
    }
    
    public static BigDecimal toBigDecimal(Object arg, BigDecimal defaultValue)
    {
        if (null == arg)
        {
            return defaultValue;
        }
        
        Object value = convert(arg, BigDecimal.class);
        if (null == value)
        {
            return defaultValue;
        }
        return (BigDecimal) value;
    }
    
    public static Float toFloat(Object arg, Float defaultValue)
    {
        if (null == arg)
        {
            return defaultValue;
        }
        
        Object value = convert(arg, Float.class);
        if (null == value)
        {
            return defaultValue;
        }
        return (Float) value;
    }
    
    public static String toString(Object arg, String defaultValue)
    {
        if (null == arg)
        {
            return defaultValue;
        }
        
        Object value = convert(arg, String.class);
        if (null == value)
        {
            return defaultValue;
        }
        return (String) value;
    }
    
    public static Boolean toBoolean(Object arg, Boolean defaultValue)
    {
        if (null == arg)
        {
            return defaultValue;
        }
        
        Object value = convert(arg, Boolean.class);
        if (null == value)
        {
            return defaultValue;
        }
        return (Boolean) value;
    }
    
    public static String toStringFromBlob(byte[] blob, String defaultValue)
    {
        if (null == blob)
        {
            return defaultValue;
        }
        
        Object value = convert(blob, BlobStringType.class);
        
        if (null == value)
        {
            return defaultValue;
        }
        
        return (String) value;
    }
    
    public static String toStringFromStream(InputStream in, String defaultValue) throws IOException
    {
        if (null == in)
        {
            return defaultValue;
        }
        
        Object value = convert(in, StreamStringType.class);
        
        if (null == value)
        {
            return defaultValue;
        }
        
        return (String) value;
    }
    
    /**
     * 将16进制的字符串，转换为long
     */
    public static Long toHexLong(String str, Long defaultValue)
    {
        if (StringUtils.isBlank(str))
        {
            return defaultValue;
        }
        
        Object obj = convert(str, HexLongType.class);
        
        if (null == obj)
        {
            return defaultValue;
        }
        
        return (Long) obj;
    }
    
    /**
     * 将一个字符串转换为一个对象实例
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static Object toObjectInstance(String str, Object defaultValue)
    {
        if (StringUtils.isBlank(str))
        {
            return defaultValue;
        }
        
        Object obj = convert(str, ClassNameObjectType.class);
        
        if (null == obj)
        {
            return defaultValue;
        }
        
        return obj;
    }
}

class HexToLongConvert implements Converter
{
    public Object convert(Class type, Object arg)
    {
        if (null == arg || !(arg instanceof String))
        {
            return null;
        }
        
        String str = (String) arg;
        if (StringUtils.isBlank(str))
        {
            return null;
        }
        
        str = StringUtils.upperCase(str);
        str = str.replaceFirst("0X", "").replaceAll("L", "");
        
        return Long.parseLong(str, 16);
    }
}

class BlobToStringConvert implements Converter
{
    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Class type, Object arg)
    {
        if (null == arg || !(arg instanceof byte[]))
        {
            return null;
        }
        
        try
        {
            return new String((byte[]) arg, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException("utf8", e);
        }
    }
}


class StreamToStringConvert implements Converter
{
    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Class type, Object arg)
    {
        if (null == arg || !(arg instanceof InputStream))
        {
            return null;
        }
        
        InputStream in = (InputStream) arg;
        
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder content = new StringBuilder();
            String line = br.readLine();
            while (line != null)
            {
                content.append(line).append('\n');
                line = br.readLine();
            }
            
            return content.toString();
        }
        catch (IOException e)
        {
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(br);
        }
    }
}

/** 将一个class name字符串，实例化为一个该类的对象 */
class ClassNameToInstanceConvert implements Converter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassNameToInstanceConvert.class);
    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Class type, Object arg)
    {
        if (null == arg || !(arg instanceof String))
        {
            return null;
        }
        try
        {
            Class clazz = Class.forName((String) arg);
            return clazz.newInstance();
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalArgumentException((String) arg, e);
        }
        catch (InstantiationException e)
        {
            throw new IllegalArgumentException((String) arg, e);
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalArgumentException((String) arg, e);
        }
    }
}

class HexLongType
{
}

class BlobStringType
{
}

class StreamStringType
{
}

class ClassNameObjectType
{
}
