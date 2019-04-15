package com.dhjt.util;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ZipUtilTest {

	@Test
	public void testDeCompress() {
		String srcPathName1 = "C:/Users/sun/Desktop/test1/";
		String srcPathName2 = "C:/Users/sun/Desktop/test2/";
		String zipPath1 = "C:/Users/sun/Desktop/test1.zip";
		String zipPath = "C:/Users/sun/Desktop/test.zip";
		ZipUtil.compress(zipPath1,srcPathName1);
		ZipUtil.compress(zipPath,srcPathName1,srcPathName2);
		String sourceFile = "C:/Users/sun/Desktop/test.zip";
		String destDir = "C:/Users/sun/Desktop/test";
		try {
//			ZipUtil.deCompress(sourceFile, destDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertNotNull(email);
	}

	@AfterClass
	public void after() {
		System.out.println("2018年12月31日下午10:43:34->" + 121);
	}
}
