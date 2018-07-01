package com.dhjt.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author DHJT 2018年2月28日-下午10:15:15
 * @version
 */
public class Identities {

	private static SecureRandom random = new SecureRandom();

	private static long currentId = 0;

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	/**
	 * 返回一个唯一标志数字，可用作主键值
	 *
	 * @return
	 */
	public synchronized static String generateId() {
//		long id = new Date().getTime();
		long id = System.currentTimeMillis();
		if (currentId < id) {
			currentId = id;
		} else {
			currentId++;
		}
		return String.valueOf(currentId);
	}

	public static void main(String[] args) {
	    System.out.println("2018年6月25日 下午4:15:06->" + randomLong());
	    System.out.println("2018年6月25日 下午4:15:06->" + randomBase62(12));
    }
}
