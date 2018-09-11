package org.demo.hibernateAndHSearch;

import java.util.ArrayList;
import java.util.List;

import org.demo.hibernateAndHSearch.bean.LuceneBean;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * Hiberante Search使用
 * @author DHJT 2018年9月10日 下午2:28:44
 *
 */
public class SearchDemo {

	public PageModel<LuceneBean> searchArticle(int pageNum, int pageSize, String keyword) {
		FullTextSession fts = Search.getFullTextSession(sessionFactory.getCurrentSession());
		QueryBuilder qb = fts.getSearchFactory().buildQueryBuilder().forEntity(LuceneBean.class).get();
		Query luceneQuery = qb.keyword().onFields("title", "content").matching(keyword).createQuery();
		FullTextQuery query = fts.createFullTextQuery(luceneQuery, LuceneBean.class);
		query.setFirstResult((pageNum -1) * pageSize);
		query.setMaxResults(pageSize);
		List<LuceneBean> data = query.list();
		PageModel<LuceneBean> model = new PageModel<>(pageNum, pageSize, data.size());
		model.setData(SearchUtils.)
		return model;
	}

}
