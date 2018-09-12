package org.demo.hibernateAndHSearch;

import java.util.List;

import javax.persistence.EntityManager;

import org.demo.hibernateAndHSearch.bean.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 * 测试可以使用
 * @author DHJT 2018年9月12日-下午3:33:04
 */
public class App {

	private static SessionFactory factory;
	private static EntityManager entityManager;

	public static Session getSession() {
		// 但在5.1.0版本汇总，hibernate则采用如下新方式获取：
		// 1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
        // 在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        // 从会话工厂获取一个session
		Session session = sessionFactory.openSession();
		return session;
	}

	public static void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 但在5.1.0版本汇总，hibernate则采用如下新方式获取：
		// 1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
        // 在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        // 从会话工厂获取一个session
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try{
//	         Book book = new Book();
//	         Set<Author> set = new HashSet<Author>();
//	         Author author = new Author();
////	         author.setId(1);
//	         author.setName("DH");
//	         set.add(author);
//	         book.setAuthors(set);
//	         book.setPublicationDate(new Date());
//	         book.setTitle("-----------测试-----------");
//	         book.setSubtitle("tes");
////	         book.setId(12);
////	         session.saveOrUpdate(book);
//	         session.persist(book);
	         Employee e = new Employee("连海", "孙", 1);
	         session.persist(e);
	         session.save(e);
	         t.commit();
//
	         String hql = "FROM Employee";
	         Query query = session.createQuery(hql);
	         List results = query.list();
	         System.out.println("-----------" + results.size());

//	         index();
//	         FullTextSession fullTextSession = Search.getFullTextSession(session);
//	         fullTextSession.createIndexer().startAndWait();
	      }catch (Throwable ex) {
	         System.err.println("Failed to create sessionFactory object." + ex);
	         t.rollback();
	         throw new ExceptionInInitializerError(ex);
	      } finally {
	    	  session.close();
	    	  sessionFactory.close();
	      }
//	      ManageEmployee ME = new ManageEmployee();
//
//	      /* Add few employee records in database */
//	      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
//	      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
//	      Integer empID3 = ME.addEmployee("John", "Paul", 10000);
//
//	      /* List down all the employees */
//	      ME.listEmployees();
//
//	      /* Update employee's records */
//	      ME.updateEmployee(empID1, 5000);
//
//	      /* Delete an employee from the database */
//	      ME.deleteEmployee(empID2);
//
//	      /* List down new list of the employees */
//	      ME.listEmployees();
	}
	public static void index() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LuceneBean");
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		EntityManager em = emf.createEntityManager();
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		try {
//			fullTextEntityManager.createIndexer().startAndWait();
//			System.out.println("2018年9月10日下午4:09:51->索引建立完成");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println("2018年9月10日下午4:09:51->索引建立失败");
//		}
	}
}
