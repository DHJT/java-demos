package org.dhjt.JarTest.bean;

/**
 * 测试bean
 * @author DHJT 2018年5月8日 上午9:03:18
 *
 */
public class Kale {
	private String name;

	private String className;

	Kale() {

	}

	Kale(String clsName) {
		this.className = clsName;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public static void method() {

	}
}
