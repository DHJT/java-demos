package org.demo.hibernateAndHSearch;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.jdbc.internal.Formatter;
import org.hibernate.query.Query;

public class SearchUtils {

	/**
	 * 高亮显示文章
	 *
	 * @param query {@link org.apache.lucene.search.Query}
	 * @param data 未高亮的数据
	 * @param fields 需要高亮的字段
	 * @return 高亮数据
	 */
	public Static List<LuceneBean> hightLight(Query query, List<LuceneBean> data, String... fields) {
		List<LuceneBean> result = new ArrayList<LuceneBean>();
		Formatter formatter = new SimpleHTMLFormatter("<bstyle=\"colr:red\">", "</b>");
		QueryScorer

	}
}
