package org.dhjt.JarTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 测试Google的json工具jar包
 *
 * @author DHJT 2018年5月4日 下午10:27:41
 *
 */
public class GsonTest {

	public static class Student {
		// 不添加@Expose注解等同于@Expose(deserialize = false,serialize = false) 不做任何解析
		@SerializedName("qw") //	能指定该字段在序列化成json时的名称
		private String name;

//		@Expose
		private int age;

		@Expose(serialize = true, deserialize = true)// 既可以序列化，也可以反序列化
		private boolean look;

		@Expose(serialize = false, deserialize = true)//只解析用用，也就是反序列化可以，序列化不可以
		private String status;

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

	public static void testGsonBuilder() {
		Student student = new Student();
		student.setName("xuanyouwu");
		student.setAge(26);
		Gson gsonBuilder = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation() //不对没有用@Expose注解的属性进行操作
				.enableComplexMapKeySerialization() //当Map的key为复杂对象时,需要开启该方法
				.serializeNulls() //当字段值为空或null时，依然对该字段进行转换
				.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS") //时间转化为特定格式
				.setPrettyPrinting() //对结果进行格式化,增加换行
				.disableHtmlEscaping() //防止特殊字符出现乱码
//				.registerTypeAdapter(Student.class, new UserAdapter()) //为某特定对象设置固定的序列或反序列方式，自定义Adapter需实现JsonSerializer或者JsonDeserializer接口
				.create();
		String jsonStr = gsonBuilder.toJson(student);
		log("---->javabean convert jsonStr:" + jsonStr);
	}

	public static void testGson() {
		// 如果采用new Gson()方式创建Gson，@Expose没有任何效果。需要使用 gsonBuilder.excludeFieldsWithoutExposeAnnotation()方法。
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

	public static void main(String[] args) throws Exception {
		testGsonBuilder();
	}
}
