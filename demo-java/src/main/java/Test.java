import tech.dhjt.java8.Formula;

/**
 * 测试类
 *
 * @author DHJT 2019年2月14日 下午7:27:45
 *
 */
public class Test {

	public static void main(String[] args) {
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};
		System.out.println(formula.calculate(100)); // 100.0
		System.out.println(formula.sqrt(16)); // 4.0
		System.out.println("2019年2月14日下午7:27:07->" + 111);
	}

}
