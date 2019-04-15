package tech.dhjt.demojava;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import tech.dhjt.demojava.bean.Person;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		backToString.apply("123"); // "123"
		System.out.println("Hello World!");

		Map<Integer, String> map = new HashMap<>();
		map.put(8, "64");
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val-" + i);
		}
		map.forEach((id, val) -> System.out.println(id + val));
		System.out.println(map.getOrDefault(8, "not found")); // not found
		System.out.println(map.getOrDefault(42, "not found")); // not found

		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println("2019年4月14日上午11:52:11 -> End");
	}
}
