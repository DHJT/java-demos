package org.demo.hibernateAndHSearch.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

@Entity
@Table(name = "PAGEINFO")
@Indexed(index="PageInfo")/*标记该表可索引，参数index指定存放索引信息的文件名，路径在主配置文件中指定*/
@Analyzer(impl=IKAnalyzer.class)//分词器
public class Pageinfo implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	private String id;
	private String title;
	private String content;
	private String source;
	private String summary;

	@Id
	@GeneratedValue(generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	@DocumentId  /*以字段id作为文档id*/
	public java.lang.String getId() {
		return this.id;
	}

	@Column(name = "TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	@Field(store=Store.NO)  /*可索引，但不存储*/
	public java.lang.String getTitle() {
		return this.title;
	}

	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true)
	@Field(store=Store.NO)
	public java.lang.String getContent() {
		return this.content;
	}

	@Column(name = "SOURCE", unique = false, nullable = true, insertable = true, updatable = true)
	@Field(store=Store.NO)
	public java.lang.String getSource() {
		return this.source;
	}


	@Column(name = "SUMMARY", unique = false, nullable = true, insertable = true, updatable = true)
	@Field(store=Store.NO)
	public java.lang.String getSummary() {
		return this.summary;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}



//	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
//	@JoinColumns({ @JoinColumn(name = "SITE_ID", nullable = false, insertable = false, updatable = false) })
//	@IndexedEmbedded(prefix="site_",depth=1)  /*关联检索，如field为site_name实则是按关联表的那么属性检索*/
//	public GrabageSiteconfig getGrabageSiteconfig() {
//		return grabageSiteconfig;
//	}

}
