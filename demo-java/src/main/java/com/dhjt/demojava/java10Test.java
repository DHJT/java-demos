package com.dhjt.demojava;

import java.util.ArrayList;

/**
 * java10测试类
 * @author DHJT 2018年12月14日 下午1:25:21
 *
 */
public class java10Test {
	public static void main(String[] args) {
		var test = "121";
		System.out.println("2018年9月13日下午12:50:00->" + test);
		var x = new ArrayList<String>();
		x.add("hello，world！");// 而这句代码会编译通过
//		List strs = List.of("Hello", "World");
//
//		List intsList = List.of(1, 2, 3);
//
//		Set sets = Set.of("Hello", "World");
//
//		Set ints = Set.of(1, 2, 3);
//
//		Map maps = Map.of("Hello", 1, "World", 2);
	}
}
