package org.demo.easypoitest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 *
 * @author DHJT 2018年5月9日 下午5:14:41
 *
 */
public class Test {

	public static void main(String[] args) {
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		StudentEntity se = new StudentEntity();
		se.setId("1");
		se.setBirthday(new Date());
		se.setName("zhangsan");
		se.setSex(2);
		list.add(se);
		StudentEntity se1 = new StudentEntity();
		se1.setId("2");
		se1.setBirthday(new Date());
		se1.setName("lisi");
		se1.setSex(1);
		list.add(se1);
//		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), StudentEntity.class, list);
		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream("C:/Workspaces/ExcelExportHasImgTest.exportCompanyImg.xls");
//			workbook.write(fos);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		List<CourseEntity> listC = new ArrayList<CourseEntity>();
		CourseEntity ce = new CourseEntity();
		ce.setStudents(list);
		ce.setId("1");
		ce.setName("中文");
		TeacherEntity mathTeacher = new TeacherEntity();
		mathTeacher.setId("1");
		mathTeacher.setName("大海景天");
		ce.setMathTeacher(mathTeacher);
		listC.add(ce);
		Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("课程选课情况表1", "测试", "测试"), CourseEntity.class, listC);
		try {
			fos = new FileOutputStream("C:/Workspaces/ExcelExportHasImgTest.exportCompanyImg.xls");
			workbook1.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
