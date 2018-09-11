package org.demo.spring;

import org.demo.spring.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainAop {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("AopBeans.xml");
		Student student = (Student) context.getBean("student");
		student.getName();
		student.getAge();
		student.printThrowException();
	}
}
