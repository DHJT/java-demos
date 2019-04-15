package tech.dhjt.java8;

/**
 * Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法，
 * @author DHJT 2019年4月14日 上午9:52:37
 *
 */
public interface Formula {

	double calculate(int a);

	public default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
