package pw.cdmi.core.web.security;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/************************************************************
 * licence的验证生成.<br/>
 * @author Roger
 * @version isoc Service Platform, 2015年12月15日
 ************************************************************/
public class SecurityFile {

    private Licence lic;

    /**
     * 1.连接h2数据库
     * 2.获取本机的mac地址
     * 3.获取本机的ip地址
     * 4.获取当前的系统时间
     * 5.随机码
     * 6.判断系统是否修改了本机时间
     * 7.获取CPU序列号
     */

    /**
     * 获取licence文件中的信息.<br/>
     * @return
     */
    private Map<String, String> getLicenceInfo() {
        Map<String, String> map = new HashMap<String, String>();
        // 获取licence文件
        File file = new File("");
        try {
            // 读取文件内容并解密
            map.put("", "");
        } catch (NoSuchFieldError e) {
            // 没有licence，应立即失效，所有的验证都将不通过

        }

        return map;
    }

    // private boolean verifyLicence(Map<String,String> map){
    //
    // if(){
    // //结束日期验证
    // }
    // }

    /**
     * 获取数据库中的实体对象.<br/>
     */
    public void getH2Entity() {
        lic = new Licence();
    }

    class Licence {

        /** 公司名称  */
        private String companyName;

        /** 授权开始日期  */
        private Date authorizationDate;

        /** 授权截至日期 */
        private Date endDate;

        /** 授权机IP地址  */
        private String ipAddr;

        /** 授权机MAC地址  */
        private String macAddr;

        /** 授权机cpu序列号  */
        private String cpuSerial;

        /** 当前系统时间  */
        private Date systemTime;
    }

}
