package pw.cdmi.alarm;

/**
 * 
 *
 */
public interface AlarmType
{
    /** 故障 */
    int ERROR = 0; 
    
    /** 事件 */
    int EVENT = 1;
    
    
    /** 恢复 */
    int RECOVER = 2;
    
    
    /** 操作日志 */
    int LOG = 3;
    
    /** 运行 */
    int RUNTIME = 4;
}
