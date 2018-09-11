package org.demo.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Log4J2
 * @author DHJT 2018年8月17日 下午4:18:15
 *
 */
public class Log4JTest {
	static Logger log = LogManager.getLogger(Log4JTest.class.getName());

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		log.info("Going to create HelloWord Obj");
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
		log.info("Exiting the program");
	}
}
