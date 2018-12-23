package com.dhjt.JarTest;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;

/**
 * commons-configuration2 apache为java应用程序提供的一个通用的配置文件管理接口，可以支持多种配置文件格式
 * Properties files,XML documents,Windows INI files,Property list files (plist),
 * JNDI,JDBC Datasource,System properties,Applet parameters,Servlet parameters
 *
 * @author DHJT 2018年12月23日 上午11:06:50
 *
 */
public class CommonsConfiguration2 {
	public void test3() {
		try {
			Configurations configs = new Configurations();
			// setDefaultEncoding是个静态方法,用于设置指定类型(class)所有对象的编码方式。
			// 本例中是PropertiesConfiguration,要在PropertiesConfiguration实例创建之前调用。
			FileBasedConfigurationBuilder.setDefaultEncoding(PropertiesConfiguration.class, "UTF-8");
			PropertiesConfiguration propConfig = configs
					.properties(this.getClass().getClassLoader().getResource("log4j.properties"));
			System.out.println(propConfig.getString("log4j.appender.CONSOLE.Target"));
			System.out.println(propConfig.getBoolean("log4j.appender.LOGFILE.Append"));
			System.out.println(propConfig.getString("test"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
