package org.dhjt.JarTest.bean.customBean.fixed;

import java.sql.Clob;

import org.dhjt.JarTest.bean.customBean.basic.BasicDocument;


public class FileWSDoc extends BasicDocument {
	private FileWS owner ;
	private Clob ocrText;
	public Clob getOcrText() {
		return ocrText;
	}

	public void setOcrText(Clob ocrText) {
		this.ocrText = ocrText;
	}
	public FileWS getOwner() {
		return owner;
	}

	public void setOwner(FileWS owner) {
		this.owner = owner;
	}


}