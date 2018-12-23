package com.dhjt.JarTest;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Calendar;
import java.util.Date;

import org.dhjt.JarTest.bean.customBean.fixed.FileWS;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * hutool
 *
 * @author DHJT 2018年5月1日 下午9:48:55
 *
 */
public class HutoolTest {

	public static void testSecureUtil() {
		System.out.println("2018年7月3日下午4:50:51->" + SecureUtil.md5("123456"));
		System.out.println("2018年7月3日下午4:50:51->" + SecureUtil.sha1("123456"));
	}

	public static void testReUtil() {
		String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", "content", "$1-$2");
	}

	public static void testDate() {
		TimeInterval timer = DateUtil.timer();

		// 当前时间
		Date date = DateUtil.date();
		// 当前时间
		Date date2 = DateUtil.date(Calendar.getInstance());
		// 当前时间
		Date date3 = DateUtil.date(System.currentTimeMillis());
		// 当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
		String now = DateUtil.now();
		// 当前日期字符串，格式：yyyy-MM-dd
		String today = DateUtil.today();
		System.out.println("2018年5月1日下午10:26:37->" + today);

		//昨天
		System.out.println("2018年7月3日下午3:21:42->" + DateUtil.yesterday());
		//明天
		System.out.println("2018年7月3日下午3:21:42->" + DateUtil.tomorrow());
		//上周
		System.out.println("2018年7月3日下午3:21:42->" + DateUtil.lastWeek());
		//下周
		System.out.println("2018年7月3日下午3:21:42->" + DateUtil.nextWeek());
		//上个月
		System.out.println("2018年7月3日下午3:21:42->" + DateUtil.lastMonth());
		//下个月
		System.out.println("2018年7月3日下午3:21:42->" + DateUtil.nextMonth());

		timer.interval();//花费毫秒数
		timer.intervalRestart();//返回花费时间，并重置开始时间
		timer.intervalMinute();//花费分钟数
	}

	public static void testBean() {
		try {
			PropertyDescriptor[] pd = BeanUtil.getPropertyDescriptors(FileWS.class);
			System.out.println("2018年7月3日下午2:51:48->" + pd[0].getShortDescription());
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

//		ThreadUtil.excAsync(null, true);

		// 没有加密密码
//		ZipUtil.zip("C:\\Workspaces\\eclipse_oxygen\\java-demos\\demo-jartest\\jdom.xml");
		testBean();
		testDate();
		testSecureUtil();
	}
}
