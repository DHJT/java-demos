package com.dhjt.JarTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.encryption.PDEncryption;
import org.apache.pdfbox.pdmodel.encryption.SecurityHandler;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.encryption.StandardSecurityHandler;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * pdfbox测试
 * reference https://www.yiibai.com/pdfbox/
 * 1.加密、解密
 * 2.权限
 * 3.文档属性信息:xmpbox
 * 3.水印
 * 4.签名
 *
 * @author DHJT 2018年6月15日 下午5:16:38
 *
 */
public class PdfboxTest {

    private static final String ownerPassWord = "123";
    private static final String userPassWord = "456";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Set the permissions
     * @param permissions
     */
    public static void setAccessPermission(AccessPermission permissions) {
        permissions.setCanExtractContent(false); // 文档提取文字权限
        permissions.setCanModify(false); // 文档修改权限
        permissions.setCanPrint(false);// allowPrinting
        permissions.setReadOnly();
    }

    /**
     * 获取格式化后的时间信息
     *
     * @param dar
     * 时间信息
     * @return
     * @throws Exception
     */
    public static String dateFormat(Calendar calendar) throws Exception {
        if (null == calendar)
            return null;
        String date = null;
        try {
            String pattern = DATE_FORMAT;
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
        return date == null ? "" : date;
    }

    /**
     * 获取文档属性信息
     *
     * @param permissions
     * @throws Exception
     */
    public static void getPDDocumentInformation(PDDocument document) throws Exception {
        PDDocumentInformation info = document.getDocumentInformation();
        System.out.println("标题:" + info.getTitle());
        System.out.println("主题:" + info.getSubject());
        System.out.println("作者:" + info.getAuthor());
        System.out.println("关键字:" + info.getKeywords());
        System.out.println("应用程序:" + info.getCreator());
        System.out.println("pdf 制作程序:" + info.getProducer());

        System.out.println("作者:" + info.getTrapped());

        System.out.println("创建时间:" + dateFormat(info.getCreationDate()));
        System.out.println("修改时间:" + dateFormat(info.getModificationDate()));
    }

    /**
     * 设置文档属性信息
     *
     * @param permissions
     * @throws Exception
     */
    public static void setPDDocumentInformation(PDDocument document, String savePath) throws Exception {
        PDDocumentCatalog cata = document.getDocumentCatalog();
        System.out.println("2018年6月16日 下午11:15:50->" + cata.getVersion());
        cata.setVersion("1.4"); //  可以设置，不起效果，可以获得值

        PDDocumentInformation info = document.getDocumentInformation();
        info.setAuthor("DHJT-PDFBox");  //  作者
        info.setCreationDate(Calendar.getInstance());   //  创建日期
        info.setCreator("DHJT-PDFBox"); //  生成
        info.setKeywords("tets");   //  关键字
        info.setProducer("121");    //  创建工具
        info.setSubject("131"); //  主题
        info.setTitle("141");   //  标题
        info.setTrapped("False"); //  True, False, Unknown
        info.setModificationDate(Calendar.getInstance());   //  最近更新
        info.setCustomMetadataValue("qk", "161");
        document.save(savePath);
        System.out.println("2018年6月16日 下午11:15:50->" + cata.getVersion());
    }

    public static void getPDFTextStripper(PDDocument document) throws Exception {
        // 获取内容信息
        PDFTextStripper pts = new PDFTextStripper();
        String content = pts.getText(document);
        System.out.println("内容:" + content);
    }

    public static void getPDDocumentCatalog(PDDocument document) throws Exception {
        /** 文档页面信息 **/
        PDDocumentCatalog cata = document.getDocumentCatalog();
        PDPageTree pages = cata.getPages();
        int count = 1;
        for (PDPage page : pages) {
            if (null != page) {
                PDResources res = page.getResources();

                // 获取页面图片信息
//                Map imgs = res.getImages();
//                if (null != imgs) {
//                    Set keySet = imgs.keySet();
//                    Iterator it = keySet.iterator();
//                    while (it.hasNext()) {
//                        Object obj = it.next();
//                        PDXObjectImage img = (PDXObjectImage) imgs.get(obj);
//                        img.write2file(imgSavePath + count);
//                        count++;
//                    }
//                }
            }
        }
//        for (int i = 0; i < pages.size(); i++) {
//            PDPage page = pages.get(i);
//            if (null != page) {
//                PDResources res = page.findResources();
//
//                // 获取页面图片信息
//                Map imgs = res.getImages();
//                if (null != imgs) {
//                    Set keySet = imgs.keySet();
//                    Iterator it = keySet.iterator();
//                    while (it.hasNext()) {
//                        Object obj = it.next();
//                        PDXObjectImage img = (PDXObjectImage) imgs.get(obj);
//                        img.write2file(imgSavePath + count);
//                        count++;
//                    }
//                }
//            }
//        }
    }

    /**
     * 加密后的pdf文件Encrypt
     * @param ownerPassWord
     * @param userPassWord
     * @param srcpath
     * @param despath
     * @throws IOException
     */
    public static void pdfEncrypt(String ownerPassWord, String userPassWord, String srcpath, String despath) throws IOException {
        File file = new File(srcpath);
        PDDocument load = PDDocument.load(file);
        AccessPermission permissions = new AccessPermission();
        permissions.setCanExtractContent(false); // 文档提取文字权限
        permissions.setCanModify(false); // 文档修改权限
        StandardProtectionPolicy p = new StandardProtectionPolicy(ownerPassWord, userPassWord, permissions);
        //Setting the length of the encryption key
        p.setEncryptionKeyLength(128);
        SecurityHandler sh = new StandardSecurityHandler(p);
        sh.prepareDocumentForEncryption(load);
        PDEncryption encryptionOptions = new PDEncryption();
        encryptionOptions.setSecurityHandler(sh);
        load.setEncryptionDictionary(encryptionOptions);
        load.save(despath);
        // return "Greetings from Spring Boot!";
    }

    /**
     * Decrypt：  解密一个PDF文档
     * @param fileName
     * @return
     * @throws IOException
     * @throws InvalidPasswordException
     */
    public static void pdfDecrypt(String fileName, String password) throws InvalidPasswordException, IOException {
        File file = new File(fileName);
        PDDocument document = PDDocument.load(file, password);
        document.isAllSecurityToBeRemoved();
    }
    // PDFSplit怎么拆分PDF文件

    public static void createHelloPDF() {
        PDDocument doc = null;
        PDPage page = null;
        try {
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            content.drawString("hello");

            content.endText();
            content.close();
            doc.save("C:/Workspaces/TestTemp/pdfboxTest.pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 电子签名
     * @param document
     * @param output
     * @throws IOException
     */
    public static void signDetached(PDDocument document, OutputStream output) throws IOException {
        boolean externalSig=false;
        // create signature dictionary
        PDSignature signature = new PDSignature();
        signature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        signature.setSubFilter(PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED);
        signature.setName("Example User");
        signature.setLocation("Los Angeles, CA");
        signature.setReason("Testing");

        // the signing date, needed for valid signature
        signature.setSignDate(Calendar.getInstance());

        if (true)
        {
            System.out.println("Sign externally...");
            document.addSignature(signature);
            ExternalSigningSupport externalSigning =
                    document.saveIncrementalForExternalSigning(output);
            // invoke external signature service
//            byte[] cmsSignature = sign(externalSigning.getContent());
            // set signature bytes received from the service
//            externalSigning.setSignature(cmsSignature);
        }
        else
        {
            SignatureOptions signatureOptions = new SignatureOptions();
            // Size can vary, but should be enough for purpose.
            signatureOptions.setPreferredSignatureSize(SignatureOptions.DEFAULT_SIGNATURE_SIZE * 2);
            // register signature dictionary and sign interface
//            document.addSignature(signature, this, signatureOptions);

            // write incremental (only for signing purpose)
            document.saveIncremental(output);
        }
    }

    public void sign(InputStream content)
    {
        // cannot be done private (interface)
    }

    public static void main(String[] args) {
        String srcpath = "C:/Workspaces/TestTemp/test.pdf";
        String despath = "C:/Workspaces/TestTemp/test2.pdf";
        PDDocument document = null;
        try {
            // pdfEncrypt(srcpath, despath);
            getPDDocumentInformation(PDDocument.load(new File("C:/Workspaces/TestTemp/test.pdf")));
//            getPDFTextStripper(PDDocument.load(new File("C:/Workspaces/TestTemp/test.pdf")));
            createHelloPDF();
            setPDDocumentInformation(PDDocument.load(new File("C:/Workspaces/TestTemp/setPdfInfo.pdf")), "C:/Workspaces/TestTemp/setPdfInfo.pdf");
          //Closing the document
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
