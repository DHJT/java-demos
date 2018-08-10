package org.dhjt.JarTest;

import java.util.List;

import com.lkx.util.ExcelUtil;

/**
 * ExcelUtil测试类
 * @author DHJT 2018年7月5日 下午4:38:18
 *
 */
public class ExcelUtilTest {

	public static void readExcel(String src) throws Exception {
		// 1.定义需要读取的表头字段和表头对应的属性字段
		String keyValue ="手机名称:phoneName,颜色:color,售价:price";
		// 2.读取数据
		List list = ExcelUtil.readXls("C://test.xlsx", ExcelUtil.getMap(keyValue), "com.lkx.model.PhoneModel");
	}

	public static void outExcel(String des) throws Exception {
//		List<PhoneModel> list = new ArrayList<PhoneModel>();//假装这是一个有数据的集合
		String keyValue ="手机名称:phoneName,颜色:color,售价:price";
		// 1.模拟导出到磁盘位置
//		ExcelUtil.exportExcel("d:/testsss.xls",keyValue,list,"com.lkx.model.PhoneModel");
		// 2.在浏览器中直接输出
//		ExcelUtil.exportExcelOutputStream(response,keyValue,list,"com.lkx.model.PhoneModel","fileName");
	}

	public static void main(String[] args) {

	}
}
