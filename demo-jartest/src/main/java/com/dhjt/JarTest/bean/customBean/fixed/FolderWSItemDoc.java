package com.dhjt.JarTest.bean.customBean.fixed;

import java.sql.Clob;

import com.dhjt.JarTest.bean.customBean.basic.BasicDocument;


public class FolderWSItemDoc extends BasicDocument {
	private FolderWSItem owner;
	private Clob ocrText;
	public Clob getOcrText() {
		return ocrText;
	}

	public void setOcrText(Clob ocrText) {
		this.ocrText = ocrText;
	}
	public FolderWSItem getOwner() {
		return owner;
	}

	public void setOwner(FolderWSItem owner) {
		this.owner = owner;
	}

}