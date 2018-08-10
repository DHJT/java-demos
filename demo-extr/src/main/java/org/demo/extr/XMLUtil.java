package org.demo.extr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 * dom4j生成和解析xml文件
 * @author slh 2017年6月29日 下午10:18:34
 * 使用dom4j
 */
public class XMLUtil {
	final static String XML_POSTFIX = ".xml";
	final static String FILE_NAME ="待导入";

//	public static boolean creatXml(List<Pdfinfo> list,String path){
//		try {
//			Document document = DocumentHelper.createDocument();
//	        Element root = document.addElement("PDfInfo");
//	        root.addAttribute("count", String.valueOf(list.size()));
//	        for(Pdfinfo pdfinfo : list){
//	        	Element foler = root.addElement("DocPDF");
//	        	foler.addAttribute("ref", pdfinfo.getRef());
//	        	foler.addAttribute("path", pdfinfo.getPath());
//	        	for (PdfItemInfo itemInfo : pdfinfo.getItemInfo()) {
//	        		Element item = foler.addElement("ItemDocPDF");
//	        		item.addAttribute("itemNo", itemInfo.getItemNo());
//	        		item.addAttribute("path", itemInfo.getPath());
//				}
//	        }
//	        writer(document, path);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

//	public static List<Pdfinfo> readXml(String xmlPath){
//		List<Pdfinfo> pdfList = new ArrayList<Pdfinfo>();
//		SAXReader reader = new SAXReader();
//		Document doc;
//		try {
//			File info = null;
//			if (!App.isNull(xmlPath)) {
//				info = new File(xmlPath);
//			}
//			doc = reader.read(info);
//			Element root = doc.getRootElement();// upLoadInfo级别
//			// -----------------------------------条目------------------------------
//			List<Element> elements = root.elements();
//			for (Element element : elements) {
//				Pdfinfo pdfinfo = new Pdfinfo(element.attributeValue("ref"));
//				pdfinfo.setPath(element.attributeValue("path"));
//				List<Element> iElements = element.elements();
//				List<PdfItemInfo> itemList = new ArrayList<PdfItemInfo>();
//				for (Element iElement : iElements) {
//					PdfItemInfo pdfItemInfo = new PdfItemInfo();
//					pdfItemInfo.setItemNo(iElement.attributeValue("itemNo"));
//					pdfItemInfo.setPath(iElement.attributeValue("path"));
//					itemList.add(pdfItemInfo);
//				}
//				pdfinfo.setItemInfo(itemList);
//				pdfList.add(pdfinfo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return pdfList;
//	}

	 /**
     * 把document对象写入新的文件
     * path XML 绝对路径
     * @param document
     * @throws Exception
     */
    public static void writer(Document document,String path)  {
    	try {
    	    // 紧凑的格式
            // OutputFormat format = OutputFormat.createCompactFormat();
            // 排版缩进的格式
    		OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(path)), "utf-8"), format);
            // 写入
            writer.write(document);
            // 立即写入
            writer.flush();
            // 关闭操作
            writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    public static void main(String[] args) {
//    	 Document document = DocumentHelper.createDocument();// 创建根节点
//         Element root = document.addElement("Borrow");
//         root.addAttribute("id", "1");
//         root.addAttribute("dh", "2");
//         writer(document,"F:\\a.xml");
	}
}
