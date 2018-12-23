package com.dhjt.JarTest.algorithm;

public class SimpleSort {
	public static void sort(Comparable[] data) {
		// 数组长度
		int len = data.length;
		for (int i = 0; i < len; i++) {
			// 记录当前位置
			int position = i;
			// 找出最小的数，并用position指向最小数的位置
			for (int j = i + 1; j < len; j++) {
				if (data[position].compareTo(data[j]) > 0) {
					position = j;
				} // endif
			} // endfor
				// 交换最小数data[position]和第i位数的位置
			Comparable temp = data[i];
			data[i] = data[position];
			data[position] = temp;
		} // endfor
	}// endsort

	public static void main(String[] args) {
		// 在JDK1.5版本以上,基本数据类型可以自动装箱
		// int,double等基本类型的包装类已实现了Comparable接口
		Comparable[] c = { 4, 9, 23, 1, 45, 27, 5, 2 };
		sort(c);
		for (Comparable data : c) {
			System.out.println(data);
		}
	}
}
