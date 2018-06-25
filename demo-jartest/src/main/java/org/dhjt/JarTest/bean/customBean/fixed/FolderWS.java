package org.dhjt.JarTest.bean.customBean.fixed;

import java.util.HashSet;
import java.util.Set;

import org.dhjt.JarTest.bean.customBean.basic.BasicFolder;


public class FolderWS extends BasicFolder {
	private String problem;//机构问题
	private String startDate;//起始日期
	private String endDate;//终止日期
	private String keyWords;//主题词
	private String catalogueNo;//目录号
	private String seeAlsoNo;//参见号
	private String sortNo;//分类号
	private String totalNum;//总件数
	private Set<FolderWSItem> items = new HashSet<FolderWSItem>();
	private Set<FolderWSDoc> docs = new HashSet<FolderWSDoc>();




	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}


}
