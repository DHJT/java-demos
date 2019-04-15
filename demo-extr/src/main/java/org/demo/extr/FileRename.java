package org.demo.extr;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 1批量修改报名结构
 * @author DHJT 2019年3月13日 下午12:36:20
 *
 */
public class FileRename {

	public static void main(String[] args) {
//		StringUtils.re
		try {
			FileUtils.cleanDirectory(new File(""));
			FileUtils.forceDeleteOnExit(new File(""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 批量修改类的名称以及更改包名结构
	 * @return
	 */
	public static boolean rename(String filePtah) {


		return false;
	}

	public static boolean rename(File file) {

		return false;
	}
	public static void content() {
		File file = new File("");
	}
}
