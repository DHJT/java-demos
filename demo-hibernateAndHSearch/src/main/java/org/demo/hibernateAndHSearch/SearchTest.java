package org.demo.hibernateAndHSearch;

import java.util.Iterator;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.demo.hibernateAndHSearch.bean.Pageinfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class SearchTest {
	public static void main(String[] args) {
		new SearchTest().index();
	}

	//执行索引
	public void index(){
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
		Session session = sessionFactory.openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		//查出结果
		List<Pageinfo> pageinfos = session.createCriteria(Pageinfo.class).list();
		session.beginTransaction();
		//依次建立索引
		for (Iterator iterator = pageinfos.iterator(); iterator.hasNext();) {
			Pageinfo pageinfo = (Pageinfo) iterator.next();
			fullTextSession.index(pageinfo);
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("index over......");
	}

	public void searchTest() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
		Session session = sessionFactory.openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		//在字段content中检索
		QueryParser queryParser = new QueryParser(Version.LUCENE_36, "content", new IKAnalyzer());
		Query luceneqQuery=null;
		try {
			//检索含有“大风”的信息
			luceneqQuery = queryParser.parse("大风");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//执行检索，得到结果集
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneqQuery, Pageinfo.class);
		List<Pageinfo> pageinfos = fullTextQuery.list();
		//查看结果做验证
		for (Iterator iterator = pageinfos.iterator(); iterator.hasNext();) {
			Pageinfo pageinfo = (Pageinfo) iterator.next();
			System.out.println(pageinfo.getContent());
		}
	}

}
