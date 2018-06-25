package org.dhjt.JarTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.apache.commons.text.similarity.JaccardSimilarity;

/**
 * @Project ApacheCommonsLearn/demo-jartest
 * @Package org.dhjt.JarTest
 * @Author DHJT
 * @Time 下午3:39:20
 *
 *       随机字符串生成
 *
 */
public class ApacheCommonsTextTest {
    public static void main(String[] args) {
        // 使用字母a-z，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator1 = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        String randomLetters = generator1.generate(20);
        System.out.println(StringUtils.center("随机字母字符串", 20, "="));
        System.out.println(randomLetters);

        // 使用数字0-9，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator2 = new RandomStringGenerator.Builder().withinRange('0', '9').build();
        String randomNumbers = generator2.generate(20);
        System.out.println(StringUtils.center("随机数字字符串", 20, "="));
        System.out.println(randomNumbers);

        // 使用码位为0到z的字符，生成20个code point(维基百科称之为'码位')的随机字符串
        RandomStringGenerator generator3 = new RandomStringGenerator.Builder().withinRange('0', 'z').build();
        String random = generator3.generate(20);
        System.out.println(StringUtils.center("随机混合字符串", 20, "="));
        System.out.println(random);

        // 计算jaccard相似系数
        JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
        double jcdsimilary1 = jaccardSimilarity.apply("hello", "hell");
        System.out.println("jcdsimilary1:" + jcdsimilary1);
        double jcdsimilary2 = jaccardSimilarity.apply("this is an apple", "this is an app");
        System.out.println("jcdsimilary2:" + jcdsimilary2);
        // 计算余弦相似度
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        Map<CharSequence, Integer> leftVector = new HashMap<>();
        Map<CharSequence, Integer> rightVector = new HashMap<>();
        leftVector.put("a", 1);
        leftVector.put("b", 0);
        leftVector.put("c", 1);
        rightVector.put("a", 1);
        rightVector.put("b", 1);
        rightVector.put("c", 0);
        double cosSimilary = cosineSimilarity.cosineSimilarity(leftVector, rightVector);
        System.out.println("cosSimilary:" + cosSimilary);

        System.out.println("2018年6月14日 下午1:06:08->" + RandomStringUtils.random(5)); //  中文环境乱码
        System.out.println("2018年6月14日 下午1:06:08->" + RandomStringUtils.random(5, new char[]{'a','b','c','d','e','f', '1', '2', '3'}));
    }
}
