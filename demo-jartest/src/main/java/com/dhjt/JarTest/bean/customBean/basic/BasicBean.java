package com.dhjt.JarTest.bean.customBean.basic;

import java.io.Serializable;

/**
 * 系统基本字段
 *
 * @author mjl
 *
 */
public class BasicBean implements Serializable {
//	private static final long serialVersionUID = 1L;
	private boolean look; //是否可以查看
	private String id;  //ID
	private String archiveTypeId; // 档案类型
	private String archiveTypeName;// 冗余archiveTypeId对应名称
	private String createUser;  //创建人
	private String cretaeDate; //创建时间
	private String deleteUser;  //删除人
	private String deleteDate; //删除时间
	private String modiyUser; //修改人
	private String modiyDate; //修改时间
	private boolean deleted; //是否删除
	private String status;// 状态，参考app类中的archive_status // 是否归档
	private String title;// 题名、标题,系统基础显示字段
	private String address;// 格架号，库房关联字段
	private String boxNo;// 盒号，档案盒关联字段
	private String ref;// 档号字段，与档案定义关联
	private String fondsCode;// 全宗号，与全宗定义关联
	private String yearCode;// 年度，常用字段
	private String retentionPeriod;// 保管期限

	private String securityClassification;// 密级

	private String filingUser;// 归档人，用于数据权限控制

	private String filingDate;// 归档时间，用于数据权限控制

	private String filingDept;// 归档部门，用于数据权限控制

	private String oldId;// 导入数据时的老ID

	private String pageCount;// 页数（案卷为卷内总页数）

	private String archiveType;//Type V I F

	private String goBackBz;//退回备注  edit:LJB


	//----------档案馆新增
//	private String seriesCode;//分类号
//	private String archivsCode;//档案馆编码
//	private String officeArchivalCode;//室编档号
//	private String startDate;//起始日期
//	private String endDate;//结束日期
//	private String author;//责任者

	public String getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
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


}
