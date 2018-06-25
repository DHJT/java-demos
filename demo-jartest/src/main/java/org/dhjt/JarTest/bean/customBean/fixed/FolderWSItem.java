package org.dhjt.JarTest.bean.customBean.fixed;

import java.util.HashSet;
import java.util.Set;

import org.dhjt.JarTest.bean.customBean.basic.BasicFolderItem;

public class FolderWSItem extends BasicFolderItem {
	private String documentNo;// 发文编号（文号）
	private String dutyPerson;// 责任者
	private String seeAlsoNo;// 参见号
	private String keyWords;// 主题词
	private String fileDate;// 文件日期
	private String notes;// 附注
	private FolderWS owner;// 案卷封面
	private Set<FolderWSItemDoc> docs = new HashSet<FolderWSItemDoc>();

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDutyPerson() {
		return dutyPerson;
	}

	public void setDutyPerson(String dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	public String getSeeAlsoNo() {
		return seeAlsoNo;
	}

	public void setSeeAlsoNo(String seeAlsoNo) {
		this.seeAlsoNo = seeAlsoNo;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<FolderWSItemDoc> getDocs() {
		return docs;
	}

	public void setDocs(Set<FolderWSItemDoc> docs) {
		this.docs = docs;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public FolderWS getOwner() {
		return owner;
	}

	public void setOwner(FolderWS owner) {
		this.owner = owner;
	}

}
