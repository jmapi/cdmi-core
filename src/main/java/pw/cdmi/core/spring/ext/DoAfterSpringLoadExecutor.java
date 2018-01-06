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


/**
 * 
 * @author s90006125
 *
 */
public abstract class DoAfterSpringLoadExecutor
{
    private final String name;
    
    public DoAfterSpringLoadExecutor(String name)
    {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof DoAfterSpringLoadExecutor)
        {
            if (this == obj)
            {
                return true;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            DoAfterSpringLoadExecutor other = (DoAfterSpringLoadExecutor) obj;
            if (name == null)
            {
                if (other.name != null)
                {
                    return false;
                }
            }
            else if (!name.equals(other.name))
            {
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public abstract void execute();
    
    public String getName()
    {
        return this.name;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
