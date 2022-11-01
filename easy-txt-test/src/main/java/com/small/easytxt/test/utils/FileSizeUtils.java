package com.small.easytxt.test.utils;

import com.small.easytxt.utils.StringUtils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/11/1 21:11
 * @version: v1.0
 */
public class FileSizeUtils {

    public static void main(String[] args) {
        String filePath = "";
        FileSizeUtils.fileSizeTransfer(FileSizeUtils.getSize(filePath));
    }


    public static long getSize(String filePathName) {
        if (StringUtils.isEmpty(filePathName))
            return 0;
        File file = new File(filePathName);
        if (file.isFile())
            return file.length();
        return 0;
    }

    public static String fileSizeTransfer(long fileSize) {
        String mFileSize;
        DecimalFormat df = new DecimalFormat("######0.00");
        double size = (double) fileSize;
        if (size > 1024 * 1024 * 1024) {
            size = size / (1024 * 1024 * 1024);
            mFileSize = df.format(size) + " G";
        } else if (size > 1024 * 1024) {
            size = size / (1024 * 1024);
            mFileSize = df.format(size) + " MB";
        } else if (size > 1024) {
            size = size / 1024;
            mFileSize = df.format(size) + " KB";
        } else {
            mFileSize = df.format(size) + " B";
        }

        return mFileSize;
    }
}
