package com.small.easytxt.utils;
/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/10/31 029 19:37
 * @version: v1.0
 */
public final class CamelCaseUtils {

    private static final char SEPARATOR = '_';

    private CamelCaseUtils() {
    }

    public static String toCamelCase(String input) {
        if (input == null) {
            return null;
        }
        input = input.toLowerCase();
        int length = input.length();

        StringBuilder sb = new StringBuilder(length);
        boolean upperCase = false;
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}

