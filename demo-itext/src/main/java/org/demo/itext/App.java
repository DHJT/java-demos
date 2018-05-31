package org.demo.itext;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Hello world!
 *
 */
public class App {
	// 生成临时文件前缀
	final private static String prefix = "dhjt";
    // 所有者密码
	final private static String OWNERPASSWORD = "12345678";
	final private static String BASE_FONT = "C:/WINDOWS/Fonts/SIMSUN.TTC,1";

	public static String createPDF() {
    	//页面大小
    	Rectangle rect = new Rectangle(PageSize.B5.rotate());
    	//页面背景色
    	rect.setBackgroundColor(BaseColor.ORANGE);
    	//Step 1—Create a Document.
    	Document document = new Document(rect);
    	try {
    		//Step 2—Get a PdfWriter instance.
    		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Workspaces\\TestTemp\\createSamplePDF.pdf"));
    		//PDF版本(默认1.4)
    		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
    		// 设置密码为："World"
    		writer.setEncryption("Hello".getBytes(), "World".getBytes(),
    		        PdfWriter.ALLOW_SCREENREADERS,
    		        PdfWriter.STANDARD_ENCRYPTION_128);
    		setPdfAttr(document);
    		//页边空白
    		document.setMargins(10, 20, 30, 40);
			//Step 3—Open the Document.
			document.open();
			//Step 4—Add content.
			document.add(new Paragraph("Hello World"));
			//Step 5—Close the Document.
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

    public static void setPdfAttr(Document document) {
    	//文档属性
		document.addTitle("Title@sample");
		document.addAuthor("Author@rensanning");
		document.addSubject("Subject@iText sample");
		document.addKeywords("Keywords@iText");
		document.addCreator("Creator@iText");
    }

	/**
     * 水印（新）
     *
     * @param outputFile
     * @param input
     * @param waterMarkName
     * @param permission
     * @throws DocumentException
     * @throws IOException
     */
    public static String setWatermark(PdfReader reader,PdfStamper stamper, String waterMarkName) throws DocumentException,
            IOException {
    	// 设置密码
//    	stamper.setEncryption("123".getBytes(), OWNERPASSWORD.getBytes(), PdfWriter.STANDARD_ENCRYPTION_40, false);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document(reader.getPageSize(i));
            int width = (int) document.getPageSize().getWidth();
            int height = (int) document.getPageSize().getHeight();
            int maxSize = width > height ? width : height;
            int fontSize = (int) (35 * (float) maxSize / 1024);// 计算比例，按照1024分辨率来,35为1024下适应字体
            content = stamper.getOverContent(i);// 在内容上方加水印
            // content = stamper.getUnderContent(i);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            content.setGState(gs);// 透明
            content.beginText();
            // content.setGrayFill(0);
            content.setFontAndSize(base, fontSize);
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, width / 2f, height / 2f, 55);
//            content.showTextAligned(Element.ALIGN_CENTER, user.getId(), width / 1.7f, height / 2f, 55);
            // content.showTextAligned(Element.ALIGN_CENTER, "下载时间："
            // + waterMarkName + "", 300, 10, 0);
            content.endText();
        }
        stamper.close();
        return "over";
    }

    public static String setLineWatermark(PdfReader reader,PdfStamper stamper, String waterMarkName) throws DocumentException,
    IOException {
    	BaseFont base = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 使用系统字体
		int total = reader.getNumberOfPages() + 1;
		for (int i = waterMarkName.length(); i < 80; i = waterMarkName.length()) {
			waterMarkName = waterMarkName + waterMarkName;
		}
		PdfContentByte under;
		PdfGState gs = new PdfGState();
		char[] wmn = waterMarkName.toCharArray();
		int rise = 0;
		int tempInt = 0;
		for (int i = 1; i < total; i++) {
			float height = reader.getPageSize(i).getHeight();
			tempInt = (int) (height * 0.3);
			under = stamper.getOverContent(i);
			under.beginText();
			under.setFontAndSize(base, 120);
			under.setColorFill(BaseColor.DARK_GRAY);
			gs.setFillOpacity(0.2f);
			under.setGState(gs);// 透明
			for (int l = 0; l < 3; l++) {
				rise = l * tempInt; // 间距
				under.setTextMatrix(0, (float) (height * 0.3));
				for (char c : wmn) {
					under.setTextRise(rise);
					under.showText(String.valueOf(c));
					rise += 20; // 偏移量
				}
			}
			// 添加水印文字
			under.endText();
		}
		return "test";
    }
    /**
     * 斜向上的水印【重复】
     * @param reader
     * @param stamper
     * @param waterMarkName
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static String setLineQXWatermark(PdfReader reader,PdfStamper stamper, String waterMarkName) throws DocumentException,
    IOException {
    	BaseFont base = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 使用系统字体
    	int total = reader.getNumberOfPages() + 1;
		for (int i = waterMarkName.length(); i < 80; i = waterMarkName.length()) {
			waterMarkName = waterMarkName + waterMarkName;
		}
		PdfContentByte under;
		PdfGState gs = new PdfGState();
		char[] wmn = waterMarkName.toCharArray();
		int rise = 0;
		int tempInt = 0;
    	for (int i = 1; i < total; i++) {
			float height = reader.getPageSize(i).getHeight();
			rise = 400;
			tempInt = (int) (height * 0.3);
			under = stamper.getOverContent(i);
			under.beginText();
			under.setFontAndSize(base, 110);
			under.setColorFill(BaseColor.DARK_GRAY);
			gs.setFillOpacity(0.2f);
			under.setGState(gs);// 透明
			for (int l = 0; l < 3; l++) {
				rise = l * tempInt; // 间距
				under.setTextMatrix(40, 400);
				under.setTextRenderingMode(-100);
				for (char c : wmn) {
					under.setTextRise(rise);
					under.showText(String.valueOf(c));
					rise += 20; // 偏移量
				}
			}
			// 添加水印文字
			under.endText();
		}
    	return "test";
    }
    public static boolean test(String inFileName, String outFileName, String waterMarkName) {
    	try {
			PdfReader reader = new PdfReader(inFileName);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outFileName));
			BaseFont base = BaseFont.createFont(BASE_FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 使用系统字体
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte under;
			PdfGState gs = new PdfGState();
			char[] wmn = waterMarkName.toCharArray();
			int rise = 0;
			int tempInt = 0;
			setWatermark(reader, stamper, waterMarkName);
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	return true;
    }

    public static List<String> printBrowseImg(String inPath, String outPath) {
    	return printBrowseImg(new File(inPath), new File(outPath));
    }
    /**
	 * 获取档案的电子文件的img影像
	 * @param path
	 * @param list
	 * @param imgs
	 * @param className
	 * @param type
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static List<String> printBrowseImg(File inPath, File outPath) {
		List<String> imgs = new ArrayList<String>();
		try {
			String tempFileName;
			try {
				PDDocument doc = PDDocument.load(inPath);
				PDFRenderer renderer = new PDFRenderer(doc);
				int pageCount = doc.getNumberOfPages();
				for (int i = 0; i < pageCount; i++) {
					BufferedImage image = renderer.renderImage(i, 2.5f);
					tempFileName = System.currentTimeMillis() + ".png";
					ImageIO.write(image, "PNG", new File(outPath.getAbsolutePath()  + tempFileName));
					imgs.add("tempFile/" + tempFileName);
				}
			} catch (FileNotFoundException e) {
				System.out.println("2017年9月21日 下午2:44:49->" + e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return imgs;
	}

	/**
     *
     * @param destPath
     *            生成pdf文件的路劲
     * @param images
     *            需要转换的图片路径的数组
     * @throws IOException
     * @throws DocumentException
     */
    public static void imagesToPdf(String destPath, String imagesPath) {
    	// 第一步：创建一个document对象。
    	Document document = new Document();
        try {
            document.setMargins(0, 0, 0, 0);
            // 第二步：
            // 创建一个PdfWriter实例，
            PdfWriter.getInstance(document, new FileOutputStream(destPath));
            // 第三步：打开文档。
            document.open();
            // 第四步：在文档中增加图片。
            File files = new File(imagesPath);
            String[] images = files.list();
            int len = images.length;

            for (int i = 0; i < len; i++) {
                if (images[i].toLowerCase().endsWith(".bmp")) {
                    String temp = imagesPath + "\\" + images[i];
                    Image img = Image.getInstance(temp);
                    img.setAlignment(Image.ALIGN_CENTER);
                    // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                    document.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    document.newPage();
                    document.add(img);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	// 第五步：关闭文档。
        	document.close();
		}
    }

    public static void main(String[] args) {
    	String fileName = "C:/" + System.currentTimeMillis() + ".pdf";
    	String waterMarkName = "pdfUtil测试专用2017-11-19";
    	for (int i = waterMarkName.length(); i < 80; i = waterMarkName.length()) {
    		waterMarkName = waterMarkName + waterMarkName;
    	}
		String isSwitchOn = "true";
		if ("true".equals(isSwitchOn)) {
//			test("F:/2010-30年-财经委-0053.pdf", fileName, waterMarkName);
		}
		try {
			createPDF();
//			printBrowseImg("F:/2010-30年-财经委-0053.pdf", "F:/test/");
//			imagesToPdf("F:/imagesToPdf.pdf", "F:/test/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
