package com.dhjt.util;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MD5UtilTest {
  @Test
  public void testMD5() {
	  Assert.assertEquals(MD5Util.MD5("20121221"), "1F69B3D54C2F95A014EA3CC131A34D5B");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("2018年12月31日下午10:49:48->开始方法测试");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("2018年12月31日下午10:49:48->结束方法测试");
  }

}
