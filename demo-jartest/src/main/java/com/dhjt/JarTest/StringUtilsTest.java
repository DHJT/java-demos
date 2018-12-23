package com.dhjt.JarTest;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsTest {
	public static void main(String[] args) {
		System.out.println(StringUtils.right("000003", 3));
	    String[] strs = {"12", "2"};
	    System.out.println("'" + StringUtils.join(strs, ',').replaceAll(",", "','") + "'");;
	}
}
