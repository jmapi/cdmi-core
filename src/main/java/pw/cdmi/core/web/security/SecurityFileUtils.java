package pw.cdmi.core.web.security;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

/************************************************************
 * licence 相关的工具类.<br/>
 * 
 * @author Roger
 * @version isoc Service Platform, 2015年12月15日
 ************************************************************/
public class SecurityFileUtils {


	public static InetAddress getInetAddress() {
		try {
			InetAddress ia = InetAddress.getLocalHost();
			return ia;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getLocalIP(){
		return getInetAddress().getHostAddress();
	}

	public static String getLocalMac() throws SocketException {
		byte[] mac = NetworkInterface.getByInetAddress(getInetAddress()).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		System.out.println("本机MAC地址:" + sb.toString().toUpperCase());
		return sb.toString().toUpperCase();
	}
	
	public static void fileTime(){
		File file = new File("d:/123.txt");
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = file.lastModified();
		Date d = new Date(time);
		System.out.println("修改时间： " + simpleFormat.format(d)); 
	} 
	
	public static String getSystemTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}


	public static void main(String[] args) throws IOException {
		SecurityFileUtils sfu = new SecurityFileUtils();
//		List<String> list = sfu.getLocalInetAddress();
//		for (String ip : list) {
//			System.out.println(ip);
//		}
		getLocalMac();
		System.out.println(getLocalIP());
		System.out.println(getSystemTime());
		fileTime();
	}

}
