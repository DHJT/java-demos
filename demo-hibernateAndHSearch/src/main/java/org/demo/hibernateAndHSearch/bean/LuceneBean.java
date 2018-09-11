package org.demo.hibernateAndHSearch.bean;

import java.io.Serializable;

import javax.persistence.Id;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 */
@Indexed
//@Analyzer(impl = IKAnalyzer.class)
@SuppressWarnings("serial")
public class LuceneBean implements Serializable {
	private boolean look; //是否可以查看
	@Id
//	@DocumentId
	private String id;  //ID
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String archiveTypeId; // 档案类型
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String archiveTypeName;// 冗余archiveTypeId对应名称@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String createUser;  //创建人
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String cretaeDate; //创建时间
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String deleteUser;  //删除人
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String deleteDate; //删除时间
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String modiyUser; //修改人
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String modiyDate; //修改时间
//	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private boolean deleted; //是否删除
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String status;// 状态，参考app类中的archive_status // 是否归档
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String title;// 题名、标题,系统基础显示字段
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String address;// 格架号，库房关联字段
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String boxNo;// 盒号，档案盒关联字段
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String ref;// 档号字段，与档案定义关联
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String fondsCode;// 全宗号，与全宗定义关联
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String yearCode;// 年度，常用字段
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String retentionPeriod;// 保管期限
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String securityClassification;// 密级
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String filingUser;// 归档人，用于数据权限控制
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String filingDate;// 归档时间，用于数据权限控制
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String filingDept;// 归档部门，用于数据权限控制
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String oldId;// 导入数据时的老ID
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String pageCount;// 页数（案卷为卷内总页数）
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String archiveType;//Type V I F
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String goBackBz;//退回备注  edit:LJB
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String ocrtext;//ocrtext
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String className;//className
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String newref;//新REF
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String fNo;	// 显示件号、案卷号
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String docExist;	// 影像是否存在
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String bz;	// 影像是否存在
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String itemTitles;	//
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String notes;	//
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String documentNo;	//
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.YES)
	private String dutyPerson;	//


	public boolean isLook() {
		return look;
	}
	public void setLook(boolean look) {
		this.look = look;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArchiveTypeId() {
		return archiveTypeId;
	}
	public void setArchiveTypeId(String archiveTypeId) {
		this.archiveTypeId = archiveTypeId;
	}
	public String getArchiveTypeName() {
		return archiveTypeName;
	}
	public void setArchiveTypeName(String archiveTypeName) {
		this.archiveTypeName = archiveTypeName;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCretaeDate() {
		return cretaeDate;
	}
	public void setCretaeDate(String cretaeDate) {
		this.cretaeDate = cretaeDate;
	}
	public String getDeleteUser() {
		return deleteUser;
	}
	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getModiyUser() {
		return modiyUser;
	}
	public void setModiyUser(String modiyUser) {
		this.modiyUser = modiyUser;
	}
	public String getModiyDate() {
		return modiyDate;
	}
	public void setModiyDate(String modiyDate) {
		this.modiyDate = modiyDate;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getFondsCode() {
		return fondsCode;
	}
	public void setFondsCode(String fondsCode) {
		this.fondsCode = fondsCode;
	}
	public String getYearCode() {
		return yearCode;
	}
	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}
	public String getRetentionPeriod() {
		return retentionPeriod;
	}
	public void setRetentionPeriod(String retentionPeriod) {
		this.retentionPeriod = retentionPeriod;
	}
	public String getSecurityClassification() {
		return securityClassification;
	}
	public void setSecurityClassification(String securityClassification) {
		this.securityClassification = securityClassification;
	}
	public String getFilingUser() {
		return filingUser;
	}
	public void setFilingUser(String filingUser) {
		this.filingUser = filingUser;
	}
	public String getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}
	public String getFilingDept() {
		return filingDept;
	}
	public void setFilingDept(String filingDept) {
		this.filingDept = filingDept;
	}
	public String getOldId() {
		return oldId;
	}
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public String getArchiveType() {
		return archiveType;
	}
	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
	}
	public String getGoBackBz() {
		return goBackBz;
	}
	public void setGoBackBz(String goBackBz) {
		this.goBackBz = goBackBz;
	}
	public String getOcrtext() {
		return ocrtext;
	}
	public void setOcrtext(String ocrtext) {
		this.ocrtext = ocrtext;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getNewref() {
		return newref;
	}
	public void setNewref(String newref) {
		this.newref = newref;
	}
	public String getfNo() {
		return fNo;
	}
	public void setfNo(String fNo) {
		this.fNo = fNo;
	}
	public String getDocExist() {
		return docExist;
	}
	public void setDocExist(String docExist) {
		this.docExist = docExist;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getItemTitles() {
		return itemTitles;
	}
	public void setItemTitles(String itemTitles) {
		this.itemTitles = itemTitles;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
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
}
