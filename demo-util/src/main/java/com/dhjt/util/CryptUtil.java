package com.dhjt.util;

import java.security.MessageDigest;
/**
 * 加密算法：Md5,sha1等;
 * @author slh
 * @date 2017年6月29日 下午9:10:30
 */
public class CryptUtil {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	final public static String CRYPT_ENCODING_SHA1 = "sha1";
	final public static String CRYPT_ENCODING_MD5 = "md5";

	// 计算并获取CheckSum
    public static String getSHA1(String str) {
        return encode(CRYPT_ENCODING_SHA1, str);
    }

	// 计算并获取md5值
    public static String getMD5(String str) {
        return encode(CRYPT_ENCODING_MD5, str);
    }

    /**
     * md5的等效算法
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = (md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     *	加密解密算法 执行一次加密，两次解密
     * @param inStr
     * @return
     */
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    /**
     * 根据algorithm算法加密value
     * @param algorithm MD2,MD5,SHA-1,SHA-224,SHA-256,SHA-384,SHA-512 .etc.
     * @param value
     * @return
     */
    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    // 测试主函数
    public static void main(String args[]) {
        String s = "sadmin";
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + getMD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        System.out.println("解密的：" + encode("md5", null));

    }
}
