package org.demo.spring;

/**
 *
 * @author DHJT 2018年8月3日 下午4:14:36
 *
 */
public class HelloWorld {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("Your Message : " + message);
	}
}
