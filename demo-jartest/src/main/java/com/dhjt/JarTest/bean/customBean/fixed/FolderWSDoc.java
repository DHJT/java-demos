package com.dhjt.JarTest.bean.customBean.fixed;

import com.dhjt.JarTest.bean.customBean.basic.BasicDocument;


public class FolderWSDoc extends BasicDocument {
	private FolderWS owner;

	public FolderWS getOwner() {
		return owner;
	}

	public void setOwner(FolderWS owner) {
		this.owner = owner;
	}

}