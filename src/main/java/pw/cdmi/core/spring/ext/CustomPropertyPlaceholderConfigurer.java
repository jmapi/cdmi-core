package pw.cdmi.core.spring.ext;

import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import pw.cdmi.core.parser.PropertyParser;

/**
 * 自定义的文件属性处理器<br>
 * 例如属性加解密等可以在此基础上，通过添加Parser进行处理
 * 
 * @see com.huawei.framework.spring.ext.Parser
 */
public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{
    private Properties allProperties = new Properties();
    
    private List<PropertyParser> parsers;
    
    public void setParsers(List<PropertyParser> parsers)
    {
        this.parsers = parsers;
    }
    
    @Override
    protected String resolvePlaceholder(String placeholder, Properties props)
    {
        if (parsers == null || parsers.isEmpty())
        {
            return props.getProperty(placeholder);
        }
        for (PropertyParser parser : parsers)
        {
            if (parser.match(placeholder))
            {
                return parser.parse(placeholder, props);
            }
        }
        return props.getProperty(placeholder);
    }
    
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
        throws BeansException
    {
        super.processProperties(beanFactoryToProcess, props);
        for (Entry<Object, Object> entry : props.entrySet())
        {
            allProperties.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }
    
    public String getProperty(String key)
    {
        return allProperties.getProperty(key);
    }
}
