package org.demo.extr;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
/**
 * 生成n位的随机码
 * @author slh 2017-05-31
 *
 */
public class TattedCodeUtil {
	private static String strAll = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 从strAll字符串中随机获取得到n位的字符串
	 * @param n
	 * @param selectStr
	 * @return
	 */
	public static String createTattedCode(final long n) {
		return createTattedCode(n, strAll);
	}
	/**
	 * 从selectStr字符串中随机获取得到n位的字符串
	 * @param n
	 * @param selectStr
	 * @return
	 */
	public static String createTattedCode(final long n, final String selectStr) {
		if (StringUtils.isBlank(selectStr) || n == 0)
			return "";
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		int rd;
		for (int i = 0; i < n; i++) {
			rd = Math.abs(random.nextInt(selectStr.length()));
			//随机从指定的位置开始获取一个字符
			result = result.append(selectStr.substring(rd, rd + 1));
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(createTattedCode(4));
	}
}
