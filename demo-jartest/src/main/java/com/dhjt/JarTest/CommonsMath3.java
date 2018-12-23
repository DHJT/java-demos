package com.dhjt.JarTest;

import java.util.Arrays;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.OpenMapRealVector;
import org.apache.commons.math3.linear.RealVectorFormat;

/**
 * commons-math3测试类 科学计算类库
 * 向量和矩阵
 * @author DHJT 2018年12月23日 上午10:32:59
 *
 */
public class CommonsMath3 {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		// 构造稀疏向量，维数为7
		OpenMapRealVector openMapRealVector = new OpenMapRealVector(7);
		// 填充稀疏向量
		openMapRealVector.setEntry(2, 1.0);
		openMapRealVector.setEntry(4, 1.0);
		// 输出稀疏向量当前的值
		System.out.println("sparse：" + Arrays.toString(openMapRealVector.toArray()));
		double[] a = new double[] { 0, 0.2, 0.3, 0.4, 0, 0, 0 };
		// 直接以一维数组构造稠密向量
		ArrayRealVector arrayRealVector = new ArrayRealVector(a);
		System.out.println("dense：" + Arrays.toString(arrayRealVector.toArray()));
		OpenMapRealVector realVector = openMapRealVector.append(arrayRealVector);
		System.out.println("result：" + Arrays.toString(realVector.toArray()));
		String str = "0.11,0.222,0.33";
		// 用RealVector解析字符串，参数分别是字符串的前缀、后缀和分隔符
		RealVectorFormat realVectorFormat = new RealVectorFormat("", "", ",");
		ArrayRealVector arrayRealVector1 = realVectorFormat.parse(str);
		System.out.println(Arrays.toString(arrayRealVector1.toArray()));
	}
}
