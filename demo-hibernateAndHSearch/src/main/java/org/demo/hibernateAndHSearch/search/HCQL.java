package org.demo.hibernateAndHSearch.search;

import java.util.List;

import org.demo.hibernateAndHSearch.App;
import org.demo.hibernateAndHSearch.bean.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

/**
 * Hibernate标准(Criteria)查询语言(HCQL)用于根据具体条件获取记录。
 * @author DHJT 2018年9月10日 下午7:50:25
 *
 */
public class HCQL {

	public static void main(String[] args) {
		Session session = App.getSession();
		Criteria c = session.createCriteria(Employee.class);//passing Class class argument
		List<Employee> list = c.list();
		c.addOrder(Order.asc("salary"));
		list = c.list();
		System.out.println("2018年9月10日下午8:00:33->" + list.size());
		for (Employee employee : list) {
			System.out.println("2018年9月10日下午8:02:42->" + employee.getSalary());
		}
		new HCQL().testProjections(session);
	}

//	HCQL及投影
//	我们可以通过诸如name等的投影来获取特定列的数据。
	public void testProjections(Session session) {
		Criteria c = session.createCriteria(Employee.class);
		c.setProjection(Projections.property("salary"));
		List<Integer> list = c.list();
		for (Integer str : list) {
			System.out.println("2018年9月10日下午8:21:06->" + str);
		}
	}
}
