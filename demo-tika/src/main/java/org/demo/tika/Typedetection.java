package org.demo.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pkg.PackageParser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * 待处理
 * import org.apache.tika.language.LanguageIdentifier;
 * import org.apache.tika.language.detect.LanguageDetector;
 * @author DHJT 2018年5月10日 下午12:50:38
 *
 */
public class Typedetection {

	public static void Typedetection() throws Exception {
		// facade类的detect() 方法被用于检测文档类型
		// assume example.mp3 is in your current directory
		File file = new File("C:/Workspaces/TestTemp/demo.mp4");//

		// Instantiating tika facade class
		Tika tika = new Tika();

		// detecting the file type using detect method
		String filetype = tika.detect(file);
		System.out.println(filetype);

		// 从文件中提取文本
		// Assume sample.txt is in your current directory
		File file1 = new File("C:/Workspaces/TestTemp/myeclipse.ini");

		// Instantiating Tika facade class
		Tika tika1 = new Tika();
		String filecontent = tika1.parseToString(file1);
		System.out.println("Extracted Content: " + filecontent);

		// Tika语言检测
		@SuppressWarnings("deprecation")
		LanguageIdentifier identifier = new LanguageIdentifier("this is english ");
		String language = identifier.getLanguage();
		System.out.println("Language of the given content is : " + language);

		// 语言检测文档
		// Parser method parameters
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream content = new FileInputStream(file1);

		// Parsing the given document
		parser.parse(content, handler, metadata, new ParseContext());

		LanguageIdentifier object = new LanguageIdentifier(handler.toString());
		System.out.println("Language name :" + object.getLanguage());

		// 从mp4文件提取内容和元数据
		// detecting the file type
		BodyContentHandler handler1 = new BodyContentHandler();
		Metadata metadata1 = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File("C:/Workspaces/TestTemp/demo.mp4"));
		ParseContext pcontext = new ParseContext();

		// Html parser
		MP4Parser MP4Parser = new MP4Parser();
		MP4Parser.parse(inputstream, handler1, metadata1, pcontext);
		System.out.println("Contents of the document:  :" + handler1.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata1.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata1.get(name));
		}
	}

	/**
	 * 用来从XML文档提取内容和元数据
	 *
	 * @param xmlFile
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void XmlParse(String xmlFile) throws IOException, SAXException, TikaException {
		// detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(xmlFile));
		ParseContext pcontext = new ParseContext();

		// Xml parser
		XMLParser xmlparser = new XMLParser();
		xmlparser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}
	}

	/**
	 * 从HTML文档提取内容和元数据
	 *
	 * @param htmlFile
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void HtmlParse(String htmlFile) throws IOException, SAXException, TikaException {

		// detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(htmlFile));
		ParseContext pcontext = new ParseContext();

		// Html parser
		HtmlParser htmlparser = new HtmlParser();
		htmlparser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ":   " + metadata.get(name));
		}
	}

	/**
	 * Microsoft Office文档中提取内容和元数据
	 *
	 * @param excelFile
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 */
	public static void MSExcelParse(String excelFile) throws IOException, TikaException, SAXException {
		// detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(excelFile));
		ParseContext pcontext = new ParseContext();

		// OOXml parser
		OOXMLParser msofficeparser = new OOXMLParser();
		msofficeparser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}
	}

	/**
	 * 提取PDF文件内容和元数据
	 *
	 * @param pdfFile
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 */
	public static void PdfParse(String pdfFile) throws IOException, TikaException, SAXException {
//		BodyContentHandler handler = new BodyContentHandler();
		BodyContentHandler handler = new BodyContentHandler(10000000);// 增加写入的大小 int writeLimit
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(pdfFile));
		ParseContext pcontext = new ParseContext();

		// parsing the document using PDF parser
		PDFParser pdfparser = new PDFParser();
		pdfparser.parse(inputstream, handler, metadata, pcontext);

		// getting the content of the document
		System.out.println("Contents of the PDF :" + handler.toString());

		// getting metadata of the document
		System.out.println("Metadata of the PDF:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + " : " + metadata.get(name));
		}
	}

	/**
	 * JPEG图像中提取的内容和元数据
	 *
	 * @param jpegFile
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 */
	public static void JpegParse(String jpegFile) throws IOException, TikaException, SAXException {
		// detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(jpegFile));
		ParseContext pcontext = new ParseContext();

		// Jpeg Parse
		JpegParser JpegParser = new JpegParser();
		JpegParser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}
	}

	public static void PackageParse(String jarFile) throws IOException, TikaException, SAXException {
		// detecting the file type
		// BodyContentHandler handler = new BodyContentHandler();
		BodyContentHandler handler = new BodyContentHandler(10 * 1024 * 1024);
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(jarFile));
		ParseContext pcontext = new ParseContext();

		// Package parser
		PackageParser packageparser = new PackageParser();
		packageparser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document: " + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ":   " + metadata.get(name));
		}
	}

	/**
	 * 检测一个给定的文档的语言完整的程序
	 *
	 * @param File
	 * @throws IOException
	 * @throws TikaException
	 * @throws SAXException
	 */
	public static void DocumentLanguageDetection(String File) throws IOException, TikaException, SAXException {
		// Instantiating a file object
		// File file = new File("Example.txt");
		File file = new File(File);

		// Parser method parameters
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream content = new FileInputStream(file);

		// Parsing the given document
		parser.parse(content, handler, metadata, new ParseContext());

		LanguageIdentifier object = new LanguageIdentifier(handler.toString());
		System.out.println("Language name :" + object.getLanguage());
	}

	/**
	 * 从文本文件中提取元数据
	 *
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void GetMetadata(String filek) throws IOException, SAXException, TikaException {
		// Assume that boy.jpg is in your current directory
		File file = new File(filek);

		// Parser method parameters
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(file);
		ParseContext context = new ParseContext();

		parser.parse(inputstream, handler, metadata, context);
		System.out.println(handler.toString());

		// getting the list of all meta data elements
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}
	}
	public static void GetMetadata(Metadata metadata) throws IOException, SAXException, TikaException {

		// getting the list of all meta data elements
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}
	}

	public static void main(String[] args) {
		String xmlFile = "C:/Workspaces/TestTemp/spring-config.xml";
		String htmlFile = "C:/Workspaces/TestTemp/exportExcel.html";
		String excelFile = "C:/Workspaces/TestTemp/2017-11-20.xls";
		String pdfFile = "C:/Workspaces/TestTemp/2.1-2017-001-0001.pdf";
		String jpegFile = "C:/Workspaces/TestTemp/0.jpg";
		String jarFile = "C:/Workspaces/TestTemp/dom4j-1.6.1.jar";
		String txtFile = "C:/Workspaces/TestTemp/myeclipse.ini";

		try {
			// XmlParse(xmlFile);
			// HtmlParse(htmlFile);
			// MSExcelParse(excelFile);
			// PdfParse(pdfFile);
			// JpegParse(jpegFile);
			// PackageParse(jarFile);
//			DocumentLanguageDetection(txtFile);
			GetMetadata(jpegFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}
}
