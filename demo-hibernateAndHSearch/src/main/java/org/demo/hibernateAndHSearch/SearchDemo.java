package org.demo.hibernateAndHSearch;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.demo.hibernateAndHSearch.bean.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * Hiberante Search使用
 * @author DHJT 2018年9月10日 下午2:28:44
 *
 */
public class SearchDemo {
	private static SessionFactory factory;
//
//	public PageModel<LuceneBean> searchArticle(int pageNum, int pageSize, String keyword) {
//		FullTextSession fts = Search.getFullTextSession(sessionFactory.getCurrentSession());
//		QueryBuilder qb = fts.getSearchFactory().buildQueryBuilder().forEntity(LuceneBean.class).get();
//		Query luceneQuery = qb.keyword().onFields("title", "content").matching(keyword).createQuery();
//		FullTextQuery query = fts.createFullTextQuery(luceneQuery, LuceneBean.class);
//		query.setFirstResult((pageNum -1) * pageSize);
//		query.setMaxResults(pageSize);
//		List<LuceneBean> data = query.list();
//		PageModel<LuceneBean> model = new PageModel<>(pageNum, pageSize, data.size());
//		model.setData(SearchUtils.)
//		return model;
//	}
	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		// Using an Hibernate Session to rebuild an index
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		Searching();
		Searching1(fullTextSession, "Java rocks!");
	}

	public static void Searching1(FullTextSession fullTextSession, String searchString) {
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity(Book.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder
		    .keyword()
		    .onFields("title", "subtitle", "authors.name")
		    .matching(searchString)
		    .createQuery();
		FullTextQuery hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, Book.class);

		// 排序(Sorting)
		Sort sort = new Sort(new SortField("sorting_name", SortField.FIELD_SCORE.getType()));
		hibernateQuery.setSort(sort); // a FullTextQuery object
		// 分页(Pagination)
		hibernateQuery.setFirstResult(0);
		hibernateQuery.setMaxResults(5);
		// 查询的结果数量 不涉及到任何的数据库操作，它仅仅通过Lucene索引来得到结果。
		int resultSize = hibernateQuery.getResultSize();
		List<Book> result = hibernateQuery.getResultList();
		System.out.println("2018年9月12日下午5:46:16->" + result.size());
	}

	public static void Searching() {
		EntityManagerFactory entityManagerFactory = javax.persistence.Persistence.createEntityManagerFactory("filesystem");

		EntityManager em = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager =
		    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		em.getTransaction().begin();

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query parser
		// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
		    .buildQueryBuilder().forEntity(Book.class).get();
		org.apache.lucene.search.Query luceneQuery = qb
		  .keyword()
		  .onFields("title", "subtitle", "authors.name")
		  .matching("Java rocks!")
		  .createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

		// execute search
		List result = jpaQuery.getResultList();
		System.out.println("2018年9月12日下午5:46:16->" + result.size());

		em.getTransaction().commit();
		em.close();
	}

}
