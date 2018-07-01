package com.dhjt.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 工具类
 * @author DHJT 2018年6月25日 下午3:19:35
 *
 */
public class Util {

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        if ((obj != null) && (!"".equals(obj)))
            return false;
        else
            return true;
    }

    /**
     * 判断是否非空
     *
     * @param obj
     * @return
     */
    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    public static String fileCopy(String src, String des) throws IOException {
//        (new File(file + "/bqbak")).mkdirs(); // 没有文件夹则生成一个文件夹
        Util.copyFile(new File(src), new File(des));
        return des;
    }

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) {
        try {
            if (!sourceFile.exists()) {
                System.out.println("源文件不存在！已跳过！");
                return;
            }
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 新建文件输入流并对它进行缓冲
            FileInputStream input = new FileInputStream(sourceFile);
            BufferedInputStream inBuff = new BufferedInputStream(input);

            // 新建文件输出流并对它进行缓冲
            FileOutputStream output = new FileOutputStream(targetFile);
            BufferedOutputStream outBuff = new BufferedOutputStream(output);

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();

            // 关闭流
            inBuff.close();
            outBuff.close();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally { // 关闭流
//            inBuff.close();
//            outBuff.close();
//            output.close();
//            input.close();
        }
    }

 // 判断String 串 是否为只包含字母数字
    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    // 判断是否当前类中是否有当前属性
    public boolean isClassFields(Class clazz, Class superClass, String className) {

        // 先把首字母转换成小写在经行检测
        char[] chars = new char[1];
        chars[0] = className.charAt(0);
        String temp = new String(chars);
        if (chars[0] >= 'A' && chars[0] <= 'Z') {// 当为字母时，则转换为小写
            className = className.replaceFirst(temp, temp.toLowerCase());
        }

        /**
         * 循环遍历所有的元素，检测有没有这个名字
         */
        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = superClass.getDeclaredFields();
        boolean b = false;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(className)) {
                b = true;
                break;
            }
        }
        for (int k = 0; k < superFields.length; k++) {
            if (superFields[k].getName().equals(className)) {
                b = true;
                break;
            }
        }
        return b;
    }
}
