package com.dhjt.JarTest;

import static org.joor.Reflect.on;

import java.util.Map;

import org.dhjt.JarTest.bean.Kale;
import org.dhjt.JarTest.bean.customBean.fixed.FileWS;
import org.joor.Reflect;

/**
 * 测试JOOR反射包
 * test类都在这里：https://github.com/jOOQ/jOOR/tree/master/jOOR/src/test/java/org/joor/test
 * @author DHJT 2018年5月4日 下午10:45:15
 *
 */
public class JoorTest {

    public void test() {
        String world = on("java.lang.String")  // Like Class.forName()
                .create("Hello World") // Call most specific matching constructor
                .call("substring", 6)  // Call most specific matching substring() method
                .call("toString")      // Call toString()
                .get();                // Get the wrapped object, in this case a String
        System.out.println("2018年5月4日 下午10:43:24->" + world);

        String name = null;
        Kale kale;
        // 【创建类】
        kale = Reflect.on(Kale.class).create().get(); // 无参数
        kale = Reflect.on(Kale.class).create("kale class name").get();// 有参数
        System.err.println("------------------> class name = " + kale.getClassName());

        // 【调用方法】
        Reflect.on(kale).call("setName","调用setName");// 多参数
        System.err.println("调用方法：name = " + Reflect.on(kale).call("getName"));// 无参数

        // 【得到变量】
        name = Reflect.on(kale).field("name").get();// 复杂
        name = Reflect.on(kale).get("name");// 简单
        System.err.println("得到变量值： name = " + name);

        // 【设置变量的值】
        Reflect.on(kale).set("className", "hello");
        System.err.println("设置变量的值： name = " + kale.getClassName());
        System.err.println("设置变量的值： name = " + Reflect.on(kale).set("className", "hello2").get("className"));
    }

	public static void main(String[] args) {
	    FileWS fileWs = Reflect.on(FileWS.class).create().get();

	    // 得到类的所有的字段
	    Map<String, Reflect> maps = Reflect.on(FileWS.class).create().fields();
	    //	Lambda表达式
	    maps.forEach((k,v)->System.out.println("Field : " + k + " Count : "));
	}
}
