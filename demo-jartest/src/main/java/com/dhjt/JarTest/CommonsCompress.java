package com.dhjt.JarTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

import cn.hutool.core.io.FileUtil;

/**
 * working with compression and archive formats. These include: bzip2, gzip,
 * pack200, lzma, xz, Snappy, traditional Unix Compress, DEFLATE, DEFLATE64,
 * LZ4, Brotli, Zstandard and ar, cpio, jar, tar, zip, dump, 7z, arj.
 *
 * @author DHJT 2018年12月23日 上午10:43:24
 *
 */
public class CommonsCompress {

	/** Fail if a long file name is required in the archive. */
	public static final int LONGFILE_ERROR = 0;

	/** Long paths will be truncated in the archive. */
	public static final int LONGFILE_TRUNCATE = 1;

	/** GNU tar extensions are used to store long file names in the archive. */
	public static final int LONGFILE_GNU = 2;

	/**
	 * zip压缩
	 *
	 * @throws ArchiveException
	 * @throws IOException
	 */
	private void doZip() throws ArchiveException, IOException {
		ZipArchiveOutputStream zos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
				.createArchiveOutputStream("zip", new FileOutputStream(path)); // or new ZipArchiveOutputStream(new
																				// FileOutputStream(path))
		zos.setEncoding("UTF-8");
		String rootPath = FileUtil.getRootPath(files); // 获取要压缩文件根路径
		ZipArchiveEntry ze;
		for (File f : files) {
			if (!f.exists()) {
				continue;
			}

			ze = new ZipArchiveEntry(getEntryName(f, rootPath));// 获取每个文件相对路径,作为在ZIP中路径
			zos.putArchiveEntry(ze);
			// folder
			if (ze.isDirectory()) {
				zos.closeArchiveEntry();
				continue;
			}
			// file
			FileInputStream fis = new FileInputStream(f);
			IOUtils.copy(fis, zos, BUF);
			fis.close();
			zos.closeArchiveEntry();
		}

		zos.close();
	}

	private String getEntryName(File f, String rootPath) {
		String entryName;
		String fPath = f.getAbsolutePath();
		if (fPath.indexOf(rootPath) != -1) {
			entryName = fPath.substring(rootPath.length() + 1);
		} else {
			entryName = f.getName();
		}
		if (f.isDirectory()) {
			entryName += "/";// "/"后缀表示entry是文件夹
		}
		return entryName;
	}

	/**
	 * zip解压
	 *
	 * @throws IOException
	 * @throws ArchiveException
	 */
	public void doZip() throws IOException, ArchiveException {
		ZipFile file = new ZipFile(sPath, "UTF-8");
		Enumeration<ZipArchiveEntry> en = file.getEntries();
		ZipArchiveEntry ze;
		while (en.hasMoreElements()) {
			ze = en.nextElement();
			File f = new File(tPath, ze.getName());
			// 创建完整路径
			if (ze.isDirectory()) {
				f.mkdirs();
				continue;
			} else {
				f.getParentFile().mkdirs();
			}

			InputStream is = file.getInputStream(ze);
			OutputStream os = new FileOutputStream(f);
			IOUtils.copy(is, os, BUF);
			is.close();
			os.close();
		}
		file.close();
	}

	// 这是更一般的解压处理
	public void doTar() throws IOException {
		TarArchiveInputStream tis = new TarArchiveInputStream(new FileInputStream(sPath));
		TarArchiveEntry te = null;
		while ((te = tis.getNextTarEntry()) != null) {
			File target = new File(tPath, te.getName());
			if (te.isDirectory()) {
				target.mkdirs();
				continue;
			} else
				target.getParentFile().mkdirs();

			FileOutputStream fos = new FileOutputStream(target); // 将当前entry写入文件
			byte[] buf = new byte[BUF];
			int len;
			while ((len = tis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
		}
		tis.close();
	}
}
