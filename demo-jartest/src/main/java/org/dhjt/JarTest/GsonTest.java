package org.dhjt.JarTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 测试Google的json工具jar包
 *
 * @author DHJT 2018年5月4日 下午10:27:41
 *
 */
public class GsonTest {

	public static class Student {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

	private static void log(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		Student student = new Student();
		student.setName("xuanyouwu");
		student.setAge(26);
		String jsonStr = gson.toJson(student);
		log("---->javabean convert jsonStr:" + jsonStr);

		List<String> list = Arrays.asList("1", "a", "3", "rt", "5");
		log("---->list convert jsonStr:" + gson.toJson(list));

		Map<String, Object> content = new HashMap<String, Object>();
		content.put("name", "xuanyouwu");
		content.put("age", "26");
		log("---->map convert jsonStr:" + gson.toJson(content));

		String studentJsonStr = "{\"name\":\"xuanyouwu\",\"age\":26}";

		Student student1 = gson.fromJson(studentJsonStr, Student.class);
		log("------->json convert JavaBean:" + student1);
	}
}
