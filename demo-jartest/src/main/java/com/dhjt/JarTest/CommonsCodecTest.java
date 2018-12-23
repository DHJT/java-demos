package com.dhjt.JarTest;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.codec.net.URLCodec;

/**
 * commons-codec测试 Apache Commons Codec
 * Hex，Base64等待编码解码功能只是辅助于“消息摘要”算法而已。
 * 消息摘要算法的实现主要是其中的：DigestUtils
 * 主要提供的是 Base64, Hex, Phonetic and URLs 等的编码和解密。
 * 建立项目的原因是：促进 Base64 等编码算法的标准化，统一化。因为 Base64 有很多不同的实现，互不兼容。
 *
 * @author DHJT 2018年12月23日 上午9:13:39
 *
 */
public class CommonsCodecTest {

	public static void main(String[] args) {
		decodeTest(encodeTest("12341234"));
		decodeHexTest(encodeHexTest("123"));
		MD5Test("1234");
		ShaTest("123");// 40bd001563085fc35165329ea1ff5c5ecbdbbeef
		languageTest();
		try {
			URLCodec("http://baidu.com?name=你好");
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metaphone和Soundex
	 * http://350129923.blog.163.com/blog/static/17959113200763144659125/
	 * Metaphone建立出相同的key给发音相似的单字,比Soundex还要准确,但是 Metaphone没有固定长度,Soundex
	 * 则是固定第一个英文字加上3个数字. 这通常是用在类似音比对,也可以用在 MP3 的软件开发.
	 */
	private static void languageTest() {
		Metaphone metaphone = new Metaphone();
		RefinedSoundex refinedSoundex = new RefinedSoundex();
		Soundex soundex = new Soundex();

		for (int i = 0; i < 2; i++) {
			String str = (i == 0) ? "resume" : "resin";
			String mString = null;
			String rString = null;
			String sString = null;

			try {
				mString = metaphone.encode(str);
				rString = refinedSoundex.encode(str);
				sString = soundex.encode(str);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("Original:" + str);
			System.out.println("Metaphone:" + mString);
			System.out.println("RefinedSoundex:" + rString);
			System.out.println("Soundex:" + sString + "\\n");
		}
	}

	/**
	 * URLCodec
	 * @param str
	 * @throws EncoderException
	 * @throws DecoderException
	 */
	private static void URLCodec(String str) throws EncoderException, DecoderException {
		String url = "http://baidu.com?name=你好";
        URLCodec codec = new URLCodec();
        String encode = codec.encode(url);
        System.out.println(encode);
        String decode = codec.decode(encode);
        System.out.println(decode);
	}
	/**
	 * Base64编解码
	 *
	 * @param str
	 * @return
	 */
	private static String encodeTest(String str) {
		Base64 base64 = new Base64();
		try {
			str = base64.encodeToString(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("Base64 编码后：" + str);
		return str;
	}

	private static void decodeTest(String str) {
		Base64 base64 = new Base64();
		// str = Arrays.toString(Base64.decodeBase64(str));
		str = new String(Base64.decodeBase64(str));
		System.out.println("Base64 解码后：" + str);

	}

	/**
	 * Hex编解码
	 *
	 * @param str
	 * @return
	 */
	private static String encodeHexTest(String str) {
		try {
			str = Hex.encodeHexString(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("Hex 编码后：" + str);
		return str;
	}

	private static String decodeHexTest(String str) {
		Hex hex = new Hex();
		try {
			str = new String((byte[]) hex.decode(str));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		System.out.println("Hex 编码后：" + str);
		return str;
	}

	/**
	 * MD5加密
	 *
	 * @param str
	 * @return
	 */
	private static String MD5Test(String str) {

		try {
			System.out.println("MD5 编码后：" + new String(DigestUtils.md5Hex(str.getBytes("UTF-8"))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * SHA-1编码
	 *
	 * @param str
	 * @return
	 */
	private static String ShaTest(String str) {
		try {
			System.out.println("SHA 编码后：" + new String(DigestUtils.sha1Hex(str.getBytes("UTF-8"))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
