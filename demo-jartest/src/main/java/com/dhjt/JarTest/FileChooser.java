package com.dhjt.JarTest;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * javax.swing.filechooser 文件选择器
 * @author DHJT 2018年8月24日 下午10:35:17
 *
 */
public class FileChooser {
    public static void main(String[] args) {
    	String[] saveType = {"txt","java"};
        JFileChooser fc = new JFileChooser("D:");
        fc.setCurrentDirectory(new File("e:/"));//设置默认目录 打开直接默认E盘
        //是否可多选
        fc.setMultiSelectionEnabled(false);
        //选择模式，可选择文件和文件夹
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//      fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("保存文件");     //自定义选择框标题
        //设置是否显示隐藏文件
        fc.setFileHidingEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        //设置文件筛选器
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("text & java", saveType));
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	File file = fc.getSelectedFile();
        	System.out.println("2018年8月24日下午10:24:20->" + file.getName());
            File[] files = fc.getSelectedFiles();
            System.out.println("2018年8月24日下午10:24:20->" + files.length);
        }
    }
}