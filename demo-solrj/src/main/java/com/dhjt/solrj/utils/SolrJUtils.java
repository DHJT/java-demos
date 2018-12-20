package com.dhjt.solrj.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.dhjt.solrj.sample.Item;

/**
 * SolrJ接口类使用工具
 *
 * @packge com.boonya.solr.utils.SolrUtils
 * @date 2017年2月25日 下午5:32:19
 * @author pengjunlin
 * @comment
 * @update
 */
public class SolrJUtils {

	public static final String solrBaseUrl = "http://localhost:8983/solr/";
	private static SolrClient solr = null;

	static{
		try {
//			solr=SolrJUtils.buildClient(SolrJUtils.solrBaseUrl + "LuceneBean");
			solr = SolrJUtils.buildClient(SolrJUtils.solrBaseUrl + "item");
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static HttpSolrClient init(HttpSolrClient httpSolrClient)
			throws SolrServerException, IOException {
		httpSolrClient.setConnectionTimeout(100);
		httpSolrClient.setDefaultMaxConnectionsPerHost(100);
		httpSolrClient.setMaxTotalConnections(100);
		return httpSolrClient;
	}

	@SuppressWarnings("deprecation")
	public static SolrClient getClient() throws SolrServerException,
			IOException {
		HttpSolrClient httpSolrClient = new HttpSolrClient(solrBaseUrl);// early
																	// version
		init(httpSolrClient);
		return httpSolrClient;
	}

	@SuppressWarnings("deprecation")
	public static SolrClient getClient(String solrUrl)
			throws SolrServerException, IOException {
		HttpSolrClient httpSolrClient = new HttpSolrClient(solrUrl);// early
																	// version
		init(httpSolrClient);
		return httpSolrClient;
	}

	public static SolrClient buildClient() throws SolrServerException,
			IOException {

		return new HttpSolrClient.Builder(solrBaseUrl).build();// recently version
	}

	public static SolrClient buildClient(String solrUrl)
			throws SolrServerException, IOException {

		return new HttpSolrClient.Builder(solrUrl).build();// recently version
	}

	public static QueryResponse query(String keyword)
			throws SolrServerException, IOException {
		return SolrJUtils.buildClient().query(new SolrQuery(keyword));// keyword="*:*"
	}

	public static QueryResponse query(String core, String keyword)
			throws SolrServerException, IOException {
		return SolrJUtils.buildClient().query(new SolrQuery(core, keyword));// keyword="*:*"
	}

	public static QueryResponse query(SolrQuery solrQuery)
			throws SolrServerException, IOException {
		return SolrJUtils.buildClient().query(solrQuery);
	}

	public static QueryResponse query(String solrUrl, String core,
			String keyword) throws SolrServerException, IOException {
		return SolrJUtils.buildClient(solrUrl).query(
				new SolrQuery(core, keyword));// keyword="*:*"
	}

	public static QueryResponse query(String solrUrl, SolrQuery solrQuery)
			throws SolrServerException, IOException {
		return SolrJUtils.buildClient(solrUrl).query(solrQuery);
	}
	/**
	 * 根据查询条件获取从制定位置start获取rowLength个结果List<LuceneBean>
	 * @param queryStr
	 * @param start
	 * @param rowLength
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> queryBeans(Class<T> clazz, String queryStr, Integer start, Integer rowLength, boolean isHighlight) throws Exception {
		SolrQuery query = new SolrQuery();
		if (isHighlight) {
			setHighlightConfig(query, new String[] { "title", "ocrtext"}, "<font color='red'>", "</font>", 1, 100000);
		}
		setQueryOrConfig(query, Item.class, queryStr);
		query.setStart(start);
		query.setRows(rowLength);
		query.setFacet(true);
		query.addFacetField("author_s");

		QueryResponse response = solr.query(query);
		List<T> list = SolrJUtils.getBeans(clazz, response.getResults());
		if (isHighlight) {
			queryHighlight(list, response);
		}
		return list;
	}

	public static <T> List<T> queryHighlight(List<T> list, QueryResponse response) {
        Map<String, Map<String, List<String>>> highlightresult = response.getHighlighting();
        for (T t : list) {
            String id = t.getId();
            if (highlightresult.get(id) != null && highlightresult.get(id).get("title") != null) {
                t.setTitle(highlightresult.get(id).get("title").get(0));
            }
            if (highlightresult.get(id) != null && highlightresult.get(id).get("ocrtext") != null) {
                t.setOcrtext(highlightresult.get(id).get("ocrtext").get(0));
            }
        }
        return list;
    }

	/**
	 * 自己将查询的Solr结果转为实体Beans
	 * @param clazz
	 * @param solrDocumentList
	 * @return
	 * @throws Exception
	 */
	public static final <T> List<T> getBeans(Class<T> clazz, SolrDocumentList solrDocumentList) throws Exception {
	    List<T> list = new ArrayList<T>();
	    T t = null;
	    //获取所有属性
	    Field[] fields = Util.getAllField(clazz);
	    for (SolrDocument solrDocument: solrDocumentList) {
	        //反射出实例
	        t = clazz.newInstance();
	        for (Field field: fields) {
	            //判断这个属性上是否存在注解SolrField //存在的话 设置值
	            if (field.isAnnotationPresent(org.apache.solr.client.solrj.beans.Field.class)) {
	                //获取注解
	            	org.apache.solr.client.solrj.beans.Field solrField = field.getAnnotation(org.apache.solr.client.solrj.beans.Field.class);
	                if ("#default".equals(solrField.value()) || Util.isNull(solrField.value())) {
	                    // 如果注解为默认的 采用此属性的name来从solr中获取值
	                    BeanUtils.setProperty(t, field.getName(), solrDocument.get(field.getName()));
	                } else {
	                    // 如果注解为不是默认的 采用此注解上的值来从solr中获取值
	                    BeanUtils.setProperty(t, field.getName(), solrDocument.get(solrField.value()));
	                }
	            }
	        }
	        list.add(t);
	    }
	    return list;
	}

	/**
	 * 自己将查询的Solr结果转为实体Bean
	 * @param clazz
	 * @param solrDocumentList
	 * @return
	 * @throws Exception
	 */
	public static final <T> T getBean(Class<T> clazz, SolrDocument solrDocument) throws Exception {
	    //反射出实例
	    T t = clazz.newInstance();
	    //获取所有属性
	    Field[] fields = clazz.getDeclaredFields();
	    for (Field field: fields) {
	        //判断这个属性上是否存在注解SolrField //存在的话 设置值
	        if (field.isAnnotationPresent(org.apache.solr.client.solrj.beans.Field.class)) {
	            //获取注解
	        	org.apache.solr.client.solrj.beans.Field solrField = field.getAnnotation(org.apache.solr.client.solrj.beans.Field.class);
	            if ("#default".equals(solrField.value()) || Util.isNull(solrField.value())) {
	                // 如果注解为默认的 采用此属性的name来从solr中获取值
	                BeanUtils.setProperty(t, field.getName(), solrDocument.get(field.getName()));
	            } else {
	                // 如果注解为不是默认的 采用此注解上的值来从solr中获取值
	                BeanUtils.setProperty(t, field.getName(), solrDocument.get(solrField.value()));
	            }
	        }
	    }
	    return t;
	}
	/**
	 * 制定字段数组都包含同一个元素
	 * @param query
	 * @param strArray
	 * @param queryStr
	 */
	public static void setQueryAndConfig(SolrQuery query, String[] strArray, String queryStr) {
		for (String str : strArray) {
			query.addFilterQuery(str + ":" + queryStr);
		}
	}

	/**
	 * 一体化检索query设置
	 * or查询
	 * @param query
	 * @param clazz
	 * @param queryStr
	 */
	public static void setQueryOrConfig(SolrQuery query, Class clazz, String queryString) {
        Field[] fields = Util.getAllField(clazz);
        StringBuffer sb = new StringBuffer();
        int strNum = 1;
        String[] strArr = queryString.split("\\+");
        for (String queryStr : strArr) {
            sb.append("(");
            int count = 1;
            for (Field field : fields) {
                if (!"boolean".equals(field.getType().getName())) {
                    if ("java.lang.Integer".equals(field.getType().getName())) {
                        count++;
                        continue;
                    }
                    sb.append(field.getName() + ":" + queryStr);
                    if(count < fields.length) {
                        sb.append(" OR ");
                    }
                }
                count++;
            }
            sb.append(")") ;
            if(strNum < strArr.length) {
                sb.append(" AND ");
            }
            strNum++;
        }
        query.setQuery(sb.toString());
    }

	/**
	 * 设置高亮
	 * @param query
	 * @param highlightField
	 * @param pre
	 * @param post
	 * @param snippets
	 * @param fragsize
	 */
	public static void setHighlightConfig(SolrQuery query, String[] highlightFields, String pre, String post, int snippets, int fragsize) {
		query.setHighlight(true); // 开启高亮组件
		for (String field : highlightFields) {
			query.addHighlightField(field);// 高亮字段
		}
		query.setHighlightSimplePre(pre);//标记，高亮关键字前缀"<font color='red'>"
		query.setHighlightSimplePost(post);//后缀 "</font>"
		query.setHighlightSnippets(snippets);//结果分片数，默认为1
		query.setHighlightFragsize(fragsize);//每个分片的最大长度，默认为100
	}

	/**
	 * 断开服务器
	 * @param solrClient
	 * @throws IOException
	 */
    public void close(SolrClient solrClient) throws IOException {
        if(solrClient != null){
        	solrClient.close();
        	solrClient = null;
        }
    }

    /**
     * 原子更新
     * https://lucene.apache.org/solr/guide/6_6/updating-parts-of-documents.html#UpdatingPartsofDocuments-AtomicUpdates
     * @throws IOException
     * @throws SolrServerException
     */
    public static void atomicUpdates(SolrClient solr, String id, String field, String value) throws SolrServerException, IOException {
    	SolrInputDocument solrInputDocument = new SolrInputDocument();
    	solrInputDocument.addField("id", id);
    	Map<String, String > operation = new HashMap<>();
    	operation.put("set", value);
    	solrInputDocument.addField(field, operation);
    	solr.add(solrInputDocument);
    	solr.commit();
    }

    /**
     * SolrJ查询字段处理
     * @param s
     * @return
     */
    public static String escapeQueryChars(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '{' || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&' || c == ';' || c == '/' || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

	public static void main(String[] args) {
		SolrQuery query = new SolrQuery()
				.setQuery("ipod")
				.setFacet(true)
				.setFacetMinCount(1)
				.setFacetLimit(8)
				.addFacetField("category")
				.addFacetField("inStock");
		QueryResponse rsp = server.query(solrQuery);
	}
}
