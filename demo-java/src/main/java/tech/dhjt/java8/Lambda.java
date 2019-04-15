package tech.dhjt.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tech.dhjt.demojava.PersonFactory;
import tech.dhjt.demojava.bean.Person;

public class Lambda {

	static int outerStaticNum;
	int outerNum;

	void testScopes() {

		Converter<Integer, String> stringConverter1 = (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};
//		stringConverter1.)

		Converter<Integer, String> stringConverter2 = (from) -> {
			outerStaticNum = 72;
			return String.valueOf(from);
		};
	}

	public static void main(String[] args) {
		String[] strArr = new String[] {"peter", "anna", "mike", "xenia"};
		List<String> names = Arrays.asList(strArr);
		Collections.sort(names, (a, b) -> b.compareTo(a));
		names.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);
		names.forEach(System.out::println);

		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");

		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);    // 123

//		String something = "12341";
		converter = something::startsWith;
		String converted1 = converter1.convert("Java");
		System.out.println(converted1);    // "J"
	}
}
