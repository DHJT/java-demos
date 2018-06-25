package org.dhjt.JarTest.bean.customBean.basic;

import java.io.Serializable;

public class BasicDocument extends BasicBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer itemNo;// 次序号
	private Long fileSize;
	private String format;
	private String path;
	private String md5;

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
