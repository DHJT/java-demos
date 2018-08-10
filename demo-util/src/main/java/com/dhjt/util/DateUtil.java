package com.dhjt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *	日期、时间工具类
 * @author DHJT 2018-02-26
          SimpleDateFormat函数语法：

          G 年代标志符
          y 年
          M 月
          d 日
          h 时 在上午或下午 (1~12)
          H 时 在一天中 (0~23)
          m 分
          s 秒
          S 毫秒
          E 星期
          D 一年中的第几天
          F 一月中第几个星期几
          w 一年中第几个星期
          W 一月中第几个星期
          a 上午 / 下午 标记符
          k 时 在一天中 (1~24)
          K 时 在上午或下午 (0~11)
          z 时区
 */
public class DateUtil {
	// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
	final public static SimpleDateFormat DF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	final public static SimpleDateFormat DF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final public static SimpleDateFormat DF_FILE_TIME = new SimpleDateFormat("yyyyMMddhhmmssSS"); // 20180713045541350

	/**
	 * 获取当前时间：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNow() {
		return toStr(new Date(), DF_TIME);
	}
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getToday() {
		return toStr(new Date(), DF_DATE);
	}
	/**
	 * 将日期转为指定格式的字符串
	 * @param date
	 * @param sf
	 * @return
	 */
	public static String toStr(Date date, SimpleDateFormat sf) {
		if (Util.isNull(date))
			return "";
		return sf.format(date);
	}

	/**
	 * 将日期转为指定格式的字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(final Date date, final String pattern) {
		String formatedatestr = "";
		try {
			SimpleDateFormat sf = new SimpleDateFormat(pattern);
			formatedatestr = toStr(date, sf);
		} catch (final Exception e) {
			System.out.println("FormateDate error:" + e);
		}
		return formatedatestr;
	}

	/**
	 * 将日期转为字符串：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String dateToString(final Date date) {
		return toStr(date, DF_DATE);
	}

	public static boolean before(final String sdate1, final String sdate2) {
		final Date date1 = new Date(Integer.parseInt(sdate1.substring(0, 4)), Integer.parseInt(sdate1.substring(5, 7)), Integer.parseInt(sdate1
				.substring(8, 10)));
		final Date date2 = new Date(Integer.parseInt(sdate2.substring(0, 4)), Integer.parseInt(sdate2.substring(5, 7)), Integer.parseInt(sdate2
				.substring(8, 10)));
		return date1.before(date2);
	}
	/**
	 * 获取day天前的日期
	 * @param day
	 * @return
	 */
	public static String getLastDate(final long day) {
		final long date_3_hm = System.currentTimeMillis() - 3600000 * 24 * day;
		final Date date_3_hm_date = new Date(date_3_hm);

		final SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String formatedatestr = sf.format(date_3_hm_date);
		return formatedatestr;
	}

	/**
	 * @Title: getSampleDate
	 * @Description: 转换日期格式，将原日期格式：yyyy-MM-dd HH:mm:ss 转换为简单日期格式：yyyyMMdd
	 * @param dateStr
	 *            需要转换的字符串
	 * @return 处理后简单日期字符串
	 */
	public static String getSampleDate(final String dateStr) {
		final Date date = parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		return formatDate(date, "yyyyMMdd");
	}
	/**
	 * 比较两个日期之间的差值
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return 数组：day，hour，min，sec
	 */
	public static long[] dateDiff(final String startTime, final String endTime, final String format) {
		// 按照传入的格式生成一个simpledateformate对象
		final SimpleDateFormat sd = new SimpleDateFormat(format);
		final long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		final long nh = 1000 * 60 * 60;// 一小时的毫秒数
		final long nm = 1000 * 60;// 一分钟的毫秒数
		final long ns = 1000;// 一秒钟的毫秒数
		long diff;
		final long diffArr[] = new long[4];
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			final long day = diff / nd;// 计算差多少天
			final long hour = diff % nd / nh;// 计算差多少小时
			final long min = diff % nd % nh / nm;// 计算差多少分钟
			final long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
			diffArr[0] = day;
			diffArr[1] = hour;
			diffArr[2] = min;
			diffArr[3] = sec;
			System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
		} catch (final Exception e) {
			// TODO: handle exception
		}
		return diffArr;
	}

	/**
	 * 将字符串类型的日期转换为指定格式的date类型日期
	 * @param datestr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(final String datestr, final String pattern) {
		final SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(datestr);
		} catch (final ParseException e) {
			System.out.println(e);
		}
		return date;
	}

	public static void main(String[] args) {
		System.out.println(getNow());
		System.out.println(getToday());
		System.out.println(parseDate("1995-05-20", "yyyy-MM-dd"));
		System.out.println(getLastDate(new Date().getTime()));
		System.out.println(getLastDate(4));
	}
}
