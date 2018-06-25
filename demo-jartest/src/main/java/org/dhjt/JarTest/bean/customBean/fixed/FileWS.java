package org.dhjt.JarTest.bean.customBean.fixed;

import java.util.HashSet;
import java.util.Set;

import org.dhjt.JarTest.bean.customBean.basic.BasicFile;

public class FileWS extends BasicFile {
	private String documentNo;// 文号
	private String deptFileNo;// 部门件号
	private String attachment;// 附件
	private String dutyPerson;// 责任者
	private String fileDate;// 文件日期
	private String notes;// 附注
	private String catalogueNo;// 目录号
	private String keyWords;// 主题词
	private String CDNo;// 光盘号
	private String problem;// 机构问题
	private String seeAlsoNo;// 参见号
	private Set<FileWSDoc> docs = new HashSet<FileWSDoc>();

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getSeeAlsoNo() {
		return seeAlsoNo;
	}

	public void setSeeAlsoNo(String seeAlsoNo) {
		this.seeAlsoNo = seeAlsoNo;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getCDNo() {
		return CDNo;
	}

	public void setCDNo(String cDNo) {
		CDNo = cDNo;
	}

	public String getDeptFileNo() {
		return deptFileNo;
	}

	public void setDeptFileNo(String deptFileNo) {
		this.deptFileNo = deptFileNo;
	}


}
