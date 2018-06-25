package org.demo.itext7;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfResources;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.ReaderProperties;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 *  itext7 demo
 *  https://developers.itextpdf.com/examples-itext7
 * @author DHJT 2018年5月31日 下午6:11:40
 * 加密、添加属性、版本等
 * https://developers.itextpdf.com/examples/security/clone-encrypting-decrypting-pdfs
 *
 */
public class App {

    public static void test(String dest) throws Exception{
        WriterProperties wp = new WriterProperties();
        wp.setPdfVersion(PdfVersion.PDF_2_0);
        PdfWriter writer = new PdfWriter(dest, wp);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);//构建文档对象
        doc.setProperty(0, "Title@sample");
        //处理中文问题
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);//中文字体
        Table table = new Table(new float[]{2,4,4}).setWidth(UnitValue.createPercentValue(100));//构建表格以100%的宽度
        Cell cell1=new Cell().add(new Paragraph("表格1")).setFont(sysFont);//向表格添加内容
        Cell cell2=new Cell().add(new Paragraph("表格2")).setFont(sysFont);
        Cell cell3=new Cell().add(new Paragraph("表格3")).setFont(sysFont);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));//将表格添加入文档并页面居中
        doc.close();
    }

    /**
     * 设置文档版本
     * @param wp
     */
    public static void setPdfVersion(WriterProperties wp) {
        wp.setPdfVersion(PdfVersion.PDF_2_0);
    }
    /**
     * 设置文档属性信息
     * @param documentInfo
     */
    public static void setPdfAttr(PdfDocumentInfo documentInfo) {
        documentInfo.setTitle("Title@sample");
        documentInfo.setAuthor("Author@rensanning");
        documentInfo.setCreator("Creator@iText");
        documentInfo.setSubject("Subject@iText sample");
        documentInfo.setKeywords("Keywords@iText");
        documentInfo.addCreationDate();
        documentInfo.addModDate();
    }

    public static void update() throws FileNotFoundException, IOException {
        PdfReader reader = new PdfReader("C:/Workspaces/TestTemp/test.pdf", new ReaderProperties().setPassword("World".getBytes()));
        PdfWriter writer = new PdfWriter("C:/Workspaces/TestTemp/test1.pdf");
        //PDF版本(默认1.4)
//        writer.setPdfVersion(PdfWriter.);
        // 设置密码为："World"
//        writer.setEncryption("Hello".getBytes(), "World".getBytes(),
//                PdfWriter.ALLOW_SCREENREADERS,
//                PdfWriter.STANDARD_ENCRYPTION_128);
        PdfDocument pdf = new PdfDocument(reader, writer);

        setPdfAttr(pdf.getDocumentInfo());
        PdfPage firstPage = pdf.getFirstPage();
        //处理中文问题
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        PdfCanvas under = new PdfCanvas(firstPage.newContentStreamBefore(), new PdfResources(), pdf);
//        Paragraph p = new Paragraph("~~WATERMARK~~").setFontSize(72).setFontColor(Color.RED).setBold();
        Paragraph p = new Paragraph("~~WATERMARK~~").setFont(sysFont).setFontSize(72).setFontColor(null).setBold();
        float x = firstPage.getPageSize().getWidth() / 2;
        float y = firstPage.getPageSize().getHeight() / 2;
        new Canvas(under, pdf, pdf.getDefaultPageSize())
            .showTextAligned(p, x, y, 1, TextAlignment.CENTER, VerticalAlignment.MIDDLE, (float) (Math.PI / 4));
        pdf.close();
    }

    public static void parsing() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfReader("C:/Workspaces/TestTemp/test.pdf"));
        String text = PdfTextExtractor.getTextFromPage(pdf.getPage(1), new LocationTextExtractionStrategy());
        pdf.close();
        System.out.println("Extracted text:");
        System.out.println(text);
    }

    public static void createPDF() {
        try {
//            OutputStream file = new FileOutputStream(new File("E:\\pdfile\\NeedToDo\\Jiemi\\Test2.pdf"));
//            Document document = new Document("");
//            PdfWriter writer = PdfWriter.getInstance(document, file);
//            writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),
//                  PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//            document.open();
//            document.add(new Paragraph("Hello World, iText"));
//            document.add(new Paragraph(new Date().toString()));
//            document.close();
//            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void EncryptPdf (String SRC, String DEST) throws Exception {
        PdfReader reader = new PdfReader(SRC, new ReaderProperties().setPassword("World".getBytes()));
        PdfWriter writer = new PdfWriter(DEST, new WriterProperties()
                .setStandardEncryption("Hello".getBytes(), "World".getBytes(), EncryptionConstants.ALLOW_PRINTING,
                        EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA));
        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        pdfDoc.close();
    }
    private static void EncryptPdfWithoutUserPassword(String SRC, String DEST) throws Exception {
        PdfReader reader = new PdfReader(SRC, new ReaderProperties().setPassword("World".getBytes()));
        WriterProperties wp = new WriterProperties();
        wp.setPdfVersion(PdfVersion.PDF_2_0);
        wp.setStandardEncryption(null, "World".getBytes(), EncryptionConstants.ALLOW_PRINTING,
                EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA);
        PdfWriter writer = new PdfWriter(DEST, wp);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        pdfDoc.close();

    }

	public static void main(String[] args) throws FileNotFoundException, IOException {
	    try {
            test("C:/Workspaces/TestTemp/test3.pdf");
//            System.out.println("Hello World!");
//            update();
//            parsing();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
