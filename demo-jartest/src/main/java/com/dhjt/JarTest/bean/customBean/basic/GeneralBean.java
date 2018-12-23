package com.dhjt.JarTest.bean.customBean.basic;

/**
 * 法院业务通用字段
 *
 * @author mjl
 *
 */
public class GeneralBean extends BasicBean {
	private String bz;// 备注
	private String ljbm;// 立卷部门
	private String ljr;// 立卷人
	private String ljrq;// 立卷日期
	private String yjbm;// 移交部门
	private String yjr;// 移交人
	private String yjrq;// 移交日期
	private String jcr;// 检查人
	private Integer ys;// 页数（总页数）
	private String dazl;// 档案种类

	public String getDazl() {
		return dazl;
	}

	public void setDazl(String dazl) {
		this.dazl = dazl;
	}

	public Integer getYs() {
		return ys;
	}

	public void setYs(Integer ys) {
		this.ys = ys;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getLjbm() {
		return ljbm;
	}

	public void setLjbm(String ljbm) {
		this.ljbm = ljbm;
	}

	public String getLjr() {
		return ljr;
	}

	public void setLjr(String ljr) {
		this.ljr = ljr;
	}

	public String getLjrq() {
		return ljrq;
	}

	public void setLjrq(String ljrq) {
		this.ljrq = ljrq;
	}

	public String getYjbm() {
		return yjbm;
	}

	public void setYjbm(String yjbm) {
		this.yjbm = yjbm;
	}

	public String getYjr() {
		return yjr;
	}

	public void setYjr(String yjr) {
		this.yjr = yjr;
	}

	public String getYjrq() {
		return yjrq;
	}

	public void setYjrq(String yjrq) {
		this.yjrq = yjrq;
	}

	public String getJcr() {
		return jcr;
	}

	public void setJcr(String jcr) {
		this.jcr = jcr;
	}

}
