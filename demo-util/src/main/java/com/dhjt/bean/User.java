package com.dhjt.bean;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -2805189806194138597L;

	private String id;
	// 用户名称
	private String username;
	// 姓名
	private String name;
	// 电话
	private String phone;
	// 邮箱
	private String email;
	// 密码
	private String password;

	// 盐值
//	@JSONField(serialize = false)
	private String salt;

	// 用户状态：0 新增； 1 激活； 2 删除； 3 冻结；
	private String status;

	// 审批状态：0 未审批； 1 审批通过； 2 审批不通过； 3 忽略；
	private String approval;

	// 最后一次登录时间
	private String lastLoginTime;

	private Orginization orginization;

	private Integer userTx;// 审核

	public Orginization getOrginization() {
		return orginization;
	}

	public void setOrginization(Orginization orginization) {
		this.orginization = orginization;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getUserTx() {
		return userTx;
	}

	public void setUserTx(Integer userTx) {
		this.userTx = userTx;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

}
