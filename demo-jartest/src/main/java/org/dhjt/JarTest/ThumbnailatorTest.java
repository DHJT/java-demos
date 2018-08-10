package org.dhjt.JarTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 图片缩略方法测试
 * @author DHJT 2018年7月4日 下午4:14:52
 *
 */
public class ThumbnailatorTest {

	/**
	 *  图片缩略方法
	 *
	 * @param picture 图片
	 * @param length  长
	 * @param width   宽
	 * @param output  清晰程度
	 * @return 缩略后的图片
	 */
	public String abbreviations(String picture, Integer length, Integer width, Float output) {
		Random random = new Random();
		File file = new File(random.nextInt(10000) + "abbreviatedPicture.jpg");
		String s = "picture";
		try {
			Thumbnails.of(new URL(s)).size(length, width).outputQuality(output).toFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}

}
