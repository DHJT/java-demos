package com.dhjt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
/**
 * 文件操作工具类
 * @author slh
 * @date 2017年6月29日 下午10:20:50
 */
public class FileUtil {
	//测试获取图片的宽高
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		File picture = new File("D:\\EclipseWorkSpace\\workspace\\TaskNew\\webapp\\resources\\img\\head\\20150108104314981.jpg");
//		BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture));
//		System.out.println(String.format("%.1f",picture.length()/1024.0));
//		System.out.println(sourceImg.getWidth());
//		System.out.println(sourceImg.getHeight());
		System.out.println(getFileTempName("dhjt.dhjt"));
	}

	/** 获取附件保存的路径 */
//	public static String getTempFilePath() {
//		String path = App.PDF_TEMP_PATH+"\\"+App.generateId()+".pdf";
//		return path;
//	}

	/** 获取文件的名称 */
	public static String getFileTempName(String fileName) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		return simpleDateFormat.format(System.currentTimeMillis()) + "."
				+ getFileSuffix(fileName);
	}

	/** 获取文件的后缀名 */
	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	/** 获取文件的前缀名 */
	public static String getFilePerfix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**获取文件大小描述*/
	public static String formatFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

	//复制文件
	public static boolean copyFiles(String sourcePath, String targetPath) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		boolean copyflag = true;
		try {
			File srcfile = new File(sourcePath);
			File tarfile = new File(targetPath);
			File dirFolder = tarfile.getParentFile();
			if (!dirFolder.exists()) {
				dirFolder.mkdirs();
			}
			if (srcfile.exists()) {
				fis = new FileInputStream(srcfile);
				fos = new FileOutputStream(tarfile);
				byte[] buffer = new byte[20480];
				int count = 0;
				while ((count = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
			} else {
				copyflag = false;
			}
		} catch (Exception e) {
			copyflag = false;
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				copyflag = false;
				e.printStackTrace();
			}
		}
		return copyflag;
	}

	//删除file
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}

	/**
	 * 指定目录下搜索fileName的文件
	 * @param fileName
	 * @param targetFile
	 * @return
	 */
	public static File searchFile(String fileName,String targetFile){
		File result = null;
		File dir = new File(targetFile);
		File[] dirs = dir.listFiles();
		for (int i = 0; i < dirs.length; i++) {
			if(dirs[i].isDirectory()){
				result = searchFile(fileName, dirs[i].getPath());
				if(result != null){
					break;
				}
			} else if (dirs[i].isFile()) {
				if (fileName.equals(dirs[i].getName())) {
					result = dirs[i];
					break;
				}
			}
		}
		return result;
	}
	/**
	 * 删除自定的文件或者文件夹
	 * @param filepath
	 * @throws IOException
	 */
	public static void del(String filepath) throws IOException {
		del(new File(filepath));
	}
	/**
	 * 删除自定的文件或者文件夹
	 * @param filepath
	 * @throws IOException
	 */
	public static void del(File file) throws IOException {
		if (file.exists() && file.isDirectory()) {// 判断是文件还是目录
			if (file.listFiles().length == 0) {// 若目录下没有文件则直接删除
				file.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = file.listFiles();
				int i = file.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					}
					delFile[j].delete();
					// 删除文件
				}
			}
		} else {
			if (file.exists()) {
				file.delete();
			}
		}
	}

	//确认路径下的文件夹都存在
	public static void createParentDirs(String file) {
		createDir(new File(file).getParentFile());
	}

	public static void createParentDirs(File file) {
		createDir(file.getParentFile());
	}

	public static void createDirs(File[] files) {
		for (File file : files) {
			createDir(file);
		}
	}

	public static void createDirs(String[] files) {
		for (String file : files) {
			createDir(new File(file));
		}
	}

	public static void createDir(String file) {
		createDir(new File(file));
	}
	/**
	 * 创建文件夹，包括不存在的父级目录
	 *
	 * @return
	 */
	public static void createDir(File file) {
		if (!file.isDirectory() && !file.exists()) {
			file.mkdirs();
		}
	}
}
