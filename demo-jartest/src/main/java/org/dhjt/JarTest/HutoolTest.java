package org.dhjt.JarTest;

import java.util.Calendar;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;

/**
 * hutool
 *
 * @author DHJT 2018年5月1日 下午9:48:55
 *
 */
public class HutoolTest {

	public static void main(String[] args) throws Exception {
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
		System.out.println("2018年5月1日 下午10:26:37->" + today);

		ThreadUtil.excAsync(null, true);
	}
}
