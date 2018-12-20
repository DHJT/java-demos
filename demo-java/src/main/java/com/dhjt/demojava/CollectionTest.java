package com.dhjt.demojava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 集合测试类
 *
 * @author DHJT 2018年12月14日 下午1:17:29
 *
 */
public class CollectionTest {

	public static void main(String[] args) {
		new CollectionTest().foreach();
	}

	/**
	 * 测试Java8的forEach函数
	 */
	public void foreach() {
		Map<String, Object> map = new HashMap<>();
		map.put("案卷目录", "测试11111");
		map.put("卷内目录", "测试2222");

		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			System.out.println("2018年12月14日下午1:36:18->" + entry.getKey());
			System.out.println("2018年12月14日下午1:36:18->" + entry.getValue());
		}

		int sum = 0;
		List<String> aList = new ArrayList<String>();

		map.forEach((k, v) -> System.out.println("key: " + k + "; value: " + v));
//		map.forEach((k, v) -> {
//			aList.add(k);
//			System.out.println("key: " + k + "; value: " + v);
//		});

		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.forEach(System.out::println);

		list.forEach(item -> {
			if ("C".equals(item)) {
				System.out.println(item);
			}
		});
		list.stream().filter(s -> s.contains("B") || s.contains("C")).forEach(System.out::println);
		list.stream().filter(s -> s.contains("E")).findFirst().ifPresent(s -> System.out.println(s));
	}

}
