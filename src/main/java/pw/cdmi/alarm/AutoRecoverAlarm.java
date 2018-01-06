package pw.cdmi.alarm;

/**
 * 可自动恢复的告警
 *
 */
public abstract class AutoRecoverAlarm extends Alarm
{
    private static final long serialVersionUID = 2802074892773329072L;
    /**
     * 上一次发送告警的时间
     */
    private long lastAlarmTime = System.currentTimeMillis();
    
    private long alarmCycle;
    
    public AutoRecoverAlarm(String alarmID, int alarmType, int alarmLevel, String serviceName, long alarmCycle)
    {
        super(alarmID, alarmType, alarmLevel, serviceName);
        this.alarmCycle = alarmCycle;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof AutoRecoverAlarm)
        {
            return super.equals(obj);
        }
        else
        {
            return false;
        }
    }

    public long getAlarmCycle()
    {
        return alarmCycle;
    }

    public void setAlarmCycle(long alarmCycle)
    {
        this.alarmCycle = alarmCycle;
    }

    public long getLastAlarmTime()
    {
        return this.lastAlarmTime;
    }
    
    public void setLastAlarmTime(long lastAlarmTime)
    {
        this.lastAlarmTime = lastAlarmTime;
    }
    
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}
