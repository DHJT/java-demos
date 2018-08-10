package com.dhjt.util;

import java.text.SimpleDateFormat;

/**
 * Hello world!
 *
 */
public class App {

	public static Boolean getBoolean(String key) {
		return true;
	}

	public static String getString() {
		return "";
	}

	public static int getInt(String key) {
		return 12;
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SimpleDateFormat aDate=new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss  aa");
        SimpleDateFormat bDate=new SimpleDateFormat("yyyy-mmmmmm-dddddd");
        SimpleDateFormat cDate=new SimpleDateFormat("yyyyMMddhhmmssSS");
        long now=System.currentTimeMillis();
        System.out.println(aDate.format(now));
        System.out.println(bDate.format(now));
        System.out.println(cDate.format(now));
	}
}
