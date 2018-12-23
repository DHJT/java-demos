package org.demo.extr;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 邮件发送
 * @author DHJT 2017年6月27日 下午11:08:09
 * activation.jar, javax.mail-1.5.0.jar;
 */
public class MailUtil {
    // 设置服务器
    private static final String KEY_SMTP = "mail.smtp.host";
//    private static String VALUE_SMTP = "smtp.163.com";
    private static String VALUE_SMTP = "smtp.qq.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
//    private static String SEND_USER = "a1914303238@163.com";
//    private static String SEND_UNAME = "a1914303238";//发件人昵称
//    private static String SEND_PWD = "2422434359sun";
//    // 发件人用户名、密码
    private static String SEND_USER = "1914303238@qq.com";
    private static String SEND_UNAME = "1914303238@qq.com";
    private static String SEND_PWD = "fyftiocvpbpcdcfi";
    // 建立会话
    private static MimeMessage message;
    private static Session session;

    /*
     * 初始化方法
     */
    public MailUtil() {
        Properties props = System.getProperties();
        // 开启debug调试 ，打印信息
        props.setProperty("mail.debug", "true");
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");
        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.socketFactory.port", "465");
        MailSSLSocketFactory sf = null;
	    try {
			sf = new MailSSLSocketFactory();
			// 设置信任所有的主机
		    sf.setTrustAllHosts(true);
		    props.put("mail.smtp.ssl.enable", "true");
		    props.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	    // 设置信任所有的主机
	    session =  Session.getDefaultInstance(props, new Authenticator(){
              @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
              }});
	    session.setDebug(true);
        message = new MimeMessage(session);
    }

    /**
     * 发送邮件
     *
     * @param headName
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveUser
     *            收件人地址
     */
    public static void doSendHtmlEmail(String headName, String sendHtml,
            String receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
//            System.out.println("send success!");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步发送邮件（暂时还没有完成）
     * @since JDK1.8
     * @param headName
     * @param sendHtml
     * @param receiveUser
     */
	public static void doAsyncSendHtmlEmail(String headName, String sendHtml, String receiveUser) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		try {
			executorService.submit(() -> doSendHtmlEmail(headName, sendHtml, receiveUser));
		} catch (Exception e) {
			System.out.println("提交任务时发生错误" + e);
			executorService.shutdown();
		}
	}

	/**
     * 发送邮件
     * @param from 发件人
     * @param to 收件人
     * @param copyto 抄送
     * @param subject 主题
     * @param content 内容
     * @param fileList 附件列表
     * @return
     */
    public static boolean sendMail(String from, String[] to, String[] copyto, String subject, String content, String[] fileList) {
        boolean success = true;
        try {
        	MimeMessage mimeMsg = new MimeMessage(session);
        	MimeMultipart mp = new MimeMultipart();

            // 自定义发件人昵称
            String nick = "";
            try {
                nick = javax.mail.internet.MimeUtility.encodeText(SEND_UNAME);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 设置发件人
//          mimeMsg.setFrom(new InternetAddress(from));
            mimeMsg.setFrom(new InternetAddress(from, nick));
            // 设置收件人
            if (to != null && to.length > 0) {
                String toListStr = getMailList(to);
                mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toListStr));
            }
            // 设置抄送人
            if (copyto != null && copyto.length > 0) {
                String ccListStr = getMailList(copyto);
                mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccListStr));
            }
            // 设置主题
            mimeMsg.setSubject(subject);
            // 设置正文
            BodyPart bp = new MimeBodyPart();
            bp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(bp);
            // 设置附件
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    bp = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileList[i]);
                    bp.setDataHandler(new DataHandler(fds));
                    bp.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));
                    mp.addBodyPart(bp);
                }
            }
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            // 发送邮件
//            if (props.get("mail.smtp.auth").equals("true")) {
                Transport transport = session.getTransport("smtp");
//                transport.connect((String)props.get("mail.smtp.host"), (String)props.get("username"), (String)props.get("password"));
                transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
//              transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
//              transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
                transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
                transport.close();
//            } else {
//                Transport.send(mimeMsg);
//            }
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            success = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public static String getMailList(String[] mailArray) {
        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0]);
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i]);
                if (i != (length - 1)) {
                    toList.append(",");
                }
            }
        }
        return toList.toString();
    }

    public static void main(String[] args) {
        MailUtil se = new MailUtil();
//        se.doSendHtmlEmail("邮件头文件名", "邮件内容1231231", "dhjt11@qq.com");
        String from = "1914303238@qq.com";
        String[] to = {"dhjt11@qq.com", "lianhai.sun@foxmail.com"};
        String[] copyto = {"123456@163.com"};
        String subject = "测试一下";
        String content = "这是邮件内容，仅仅是测试，不需要回复.";
        String[] fileList = new String[1];
//        fileList[0] = "C:\\Note-DH\\server.xml";
        fileList[0] = "C:/Workspaces/TestTemp/0.jpg";
        sendMail(from, to, copyto, subject, content, fileList);
    }
}