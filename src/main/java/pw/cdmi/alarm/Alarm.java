package pw.cdmi.alarm;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pw.cdmi.utils.EnvironmentUtils;


public abstract class Alarm implements Serializable
{
    private static final long serialVersionUID = 2308707288249116372L;
    
    protected static final String PARAMETERS_SPLIT = ",";

    private static final String HEX_PREFIX = "0X";
    
    private static final String LONG_SUFFIX = "L";
    
//    private AlarmAffairReport alarmReport = new AlarmAffairReport();
    
    private static Logger logger = LoggerFactory.getLogger(Alarm.class);
    
    private String alarmID;
    
    //告警级别2: 提示3: 警告5: 重要6: 紧急
    private int alarmLevel;
  
    //告警类型，0: 故障1: 事件2: 恢复3：操作日志4：运行
    private int alarmType;
    
    private String serviceName;
    
    public Alarm(String alarmID, int alarmType, int alarmLevel, String serviceName)
    {
        this.setAlarmID(alarmID);
        this.setAlarmType(alarmType);
        this.setAlarmLevel(alarmLevel);
        this.setServiceName(serviceName);
    }
    
    public String getAlarmID()
    {
        return alarmID;
    }

    public void setAlarmID(String alarmID)
    {
        this.alarmID = alarmID;
        String temp = StringUtils.upperCase(StringUtils.trimToEmpty(this.alarmID))
            .replaceFirst(HEX_PREFIX, "")
            .replaceAll(LONG_SUFFIX, "");
        try
        {
//            this.alarmReport.setM_lAlarmID(Long.parseLong(temp, 16));
        }
        catch(NumberFormatException e)
        {
            logger.warn("alarmID is: " + alarmID+","+e);
            throw e;
        }
    }

    public int getAlarmLevel()
    {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel)
    {
        this.alarmLevel = alarmLevel;
//        this.alarmReport.setM_iAlarmLevel(this.alarmLevel);
    }

    public int getAlarmType()
    {
        return alarmType;
    }

    public void setAlarmType(int alarmType)
    {
        this.alarmType = alarmType;
//        this.alarmReport.setM_iAlarmType(alarmType);
    }

    public String getHostName()
    {
        return EnvironmentUtils.getHostName();
    }
    
    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

//    public AlarmAffairReport getAlarmReport()
//    {
//        return alarmReport;
//    }

    public abstract String getKey();
    
    public abstract String getParameter();
    
//    public void setAlarmReport(AlarmAffairReport alarmReport)
//    {
//        this.alarmReport = alarmReport;
//    }
    
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString(this);
    }
}
