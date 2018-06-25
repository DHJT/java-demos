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
	    System.out.println("2018年6月18日 下午7:30:01->调用Kale的空构造器");
	}

	Kale(String clsName) {
	    System.out.println("2018年6月18日 下午7:30:01->有参构造器");
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
