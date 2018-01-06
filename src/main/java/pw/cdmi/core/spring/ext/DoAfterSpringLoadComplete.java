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
package pw.cdmi.core.spring.ext;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 在Spring加载完成之后，执行一些事情
 * 
 * @author s90006125
 * 
 */
public class DoAfterSpringLoadComplete implements ApplicationListener<ContextRefreshedEvent>
{
    /** 用于避免重复执行 */
    private static boolean beenExecute = false;
    
    private static void setBeenExecute(boolean value)
    {
        DoAfterSpringLoadComplete.beenExecute = value;
    }
    
    private static Set<DoAfterSpringLoadExecutor> executors = new HashSet<DoAfterSpringLoadExecutor>(1);
    
    private final static Logger LOGGER = LoggerFactory.getLogger(DoAfterSpringLoadComplete.class);
    
    public static void regiestExecutor(DoAfterSpringLoadExecutor executor)
    {
        executors.add(executor);
    }
    
    private static void setExcecutors(Set<DoAfterSpringLoadExecutor> executors)
    {
        DoAfterSpringLoadComplete.executors = executors; 
    }
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if(beenExecute)
        {
            return;
        }
        for(DoAfterSpringLoadExecutor e: executors)
        {
            LOGGER.info("Excute the event for " + e.getName());
            e.execute();
        }
        LOGGER.info("===================End Do After Spring Load Complete ==================");
        DoAfterSpringLoadComplete.setBeenExecute(true);
    }
    
    public void setExecutors(Set<DoAfterSpringLoadExecutor> executors)
    {
        DoAfterSpringLoadComplete.setExcecutors(executors);
    }
}
