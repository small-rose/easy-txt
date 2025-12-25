package com.small.easytxt.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ FileNameUtil ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/8 12:16
 * @Version ： 1.0
 **/
public class FileNameUtil {


    public static List<String> hanler(String fileName, int size) {
        final String fileReg = fileName;
        String tmpName = "";
        List<String> numberList = null;

        List<String> strList = new ArrayList<>(size);
        if (!fileName.contains("${")) {
            strList.add(fileName);
            return strList;
        }
        int length = 0;
        String regName = fileReg.substring(fileReg.indexOf("${"), fileReg.indexOf("}") + 1);
        String regNum = regName.replace("${", "").replace("}", "");


        if (regNum.toLowerCase().contains("random")) {
            regNum = regNum.toLowerCase().replace("random", "").replace("(", "").replace(")", "");
            length = Integer.parseInt(regNum);

            numberList = generateUniqueRandomNumbers(size, length);
            for (String integer : numberList) {
                tmpName = fileReg.replace(regName, integer);
                strList.add(tmpName);
            }
            return strList;
        }
        if (regNum.toLowerCase().contains("order")) {
            regNum = regNum.toLowerCase().replace("order", "").replace("(", "").replace(")", "");
        }
        try {
            length = Integer.parseInt(regNum);
            numberList = generateNum(length, size);

            for (String integer : numberList) {
                tmpName = fileReg.replace(regName, String.valueOf(integer));
                strList.add(tmpName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("The random rule error, please use  ${random(n)} or  ${order(n)} ");
        }

        return strList;
    }

    private static List<String> generateNum(int length, int size) {
        List<String> integers = new ArrayList<>(size);
        int initNum = generateNumber(length);
        integers.add(String.valueOf(initNum));
        for (int i = 1; i < size; i++) {
            integers.add(String.valueOf(initNum + i));
        }
        return integers;
    }


    public static int generateNumber(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException("The random number of files cannot be less than 1 ");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        for (int i = 0; i < n - 1; i++) {
            sb.append("0");
        }
        return Integer.parseInt(sb.toString());
    }


    public static void main(String[] args) {

        String name = "1_abc_${5}.txt";
        List<String> hanler = hanler(name, 5);
        hanler.forEach(System.out::println);
    }


    public static List<String> generateUniqueRandomNumbers(int count, int numberOfDigits) {
        if (count > Math.pow(10, numberOfDigits)) {
            throw new IllegalArgumentException("Count cannot exceed the total number of possible combinations.");
        }

        Set<String> generatedNumbers = new HashSet<>();
        Random random = new Random();

        while (generatedNumbers.size() < count) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfDigits; i++) {
                int digit = random.nextInt(10);
                sb.append(digit);
            }
            generatedNumbers.add(sb.toString());
        }

        return new ArrayList<String>(generatedNumbers);
    }
}
