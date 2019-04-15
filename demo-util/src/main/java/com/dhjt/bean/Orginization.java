package com.dhjt.bean;

import java.io.Serializable;
import java.util.List;

public class Orginization implements Serializable{

	private String id;

	private String orgname;

	private String org;

	private String orgcode;

	private String region;

	private String type;

	private String pm;

	private Orginization parent;

	private List<Orginization> children;

	public List<Orginization> getChildren() {
		return children;
	}

	public void setChildren(List<Orginization> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public Orginization getParent() {
		return parent;
	}

	public void setParent(Orginization parent) {
		this.parent = parent;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}
}
