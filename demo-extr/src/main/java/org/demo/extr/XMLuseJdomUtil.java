package org.demo.extr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * jdom解析、生成xml工具类
 * @author DHJT 2018年2月28日 下午2:24:02
 *	jdom-2.0.2.jar;
 */
public class XMLuseJdomUtil {

	/**
	 * jdom创建xml文档
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean creatXml(String path) throws IOException {
		// 创建文档
		Document document = new Document();
		// 创建文档根元素
		Element root = new Element("root");
		// 向文档呢添加根元素
		document.addContent(root);
		// 添加注释
		Comment comment = new Comment("This is my comments");
		// 在根元素下添加注释
		root.addContent(comment);
		// 创建一个元素
		Element e = new Element("hello");
		// 给元素添加属性的第一种方式,它有返回值，返回一个Element对象
		e.setAttribute("sohu", "www.sohu.com");
		// 给根元素添加属性
		root.addContent(e);

		Element e2 = new Element("world");
		// 创建元素属性的第二种方式
		Attribute attr = new Attribute("test", "hehe");
		// 给元素添加属性
		e2.setAttribute(attr);
		// 将元素添加到根元素下
		e.addContent(e2);
		// 添加元素属性和文本内容，属于方法链的编程风格
		e2.addContent(new Element("aaa").setAttribute("a", "b").setAttribute("x", "y").setAttribute("gg", "hh")
				.setText("text content"));

		// 设置其格式,一般还有一个没有缩进与空格的格式getRawFormat，主要用于XML的网络传输，因为它没有空格，减少网络传输的数据量。
		Format format = Format.getPrettyFormat();

		// 设置元素前面空格
		format.setIndent("    ");
		// 设置编码方式
		// format.setEncoding("gbk");
		// 将XML文档输出
		XMLOutputter out = new XMLOutputter(format);
		out.output(document, new FileWriter("C:/jdom.xml"));

		return true;
	}

	/**
	 * jdom解析xml
	 * @param xmlPath
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static List<String> readXml(String xmlPath) throws JDOMException, IOException{
		SAXBuilder sb = new SAXBuilder();// 创建一个SAXBuilder对象
        Document doc = sb.build(XMLuseJdomUtil.class.getClassLoader().getResourceAsStream("test.xml"));
        // 构造文档对象
        Element root = doc.getRootElement(); // 获取根元素
        List list = root.getChildren("disk");// 取名字为disk的所有元素
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            String name = element.getAttributeValue("name");// 获取元素中属性为name的值
            String capacity = element.getChildText("capacity");// 取disk子元素capacity的内容
            String directories = element.getChildText("directories");
            String files = element.getChildText("files");
            System.out.println("磁盘信息:");
            System.out.println("分区盘符:" + name);
            System.out.println("分区容量:" + capacity);
            System.out.println("目录数:" + directories);
            System.out.println("文件数:" + files);
            System.out.println("-----------------------------------");
        }
		return null;
	}

	public static void main(String[] args) throws Exception {
//		readXml("");
		creatXml("");
	}
}
