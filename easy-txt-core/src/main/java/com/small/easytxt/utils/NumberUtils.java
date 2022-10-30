package com.small.easytxt.utils;

import com.small.easytxt.converter.ConvertData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Number utils
 *
 * @author Jiaju Zhuang
 */
public class NumberUtils {
    private NumberUtils() {}

    /**
     * format
     *
     * @param num
     * @param convertData
     * @return
     */
    public static String format(Number num, ConvertData convertData) {
        if (convertData == null || convertData.getNumberFormat() == null
            || StringUtils.isEmpty(convertData.getNumberFormat())) {
            if (num instanceof BigDecimal) {
                return ((BigDecimal)num).toPlainString();
            } else {
                return num.toString();
            }
        }
        String format = convertData.getNumberFormat();
        RoundingMode roundingMode = convertData.getRoundingMode();
        DecimalFormat decimalFormat = new DecimalFormat(format);
        decimalFormat.setRoundingMode(roundingMode);
        return decimalFormat.format(num);
    }

    /**
     * format
     *
     * @param num
     * @param convertData
     * @return
     */
    public static String formatToCellDataString(Number num, ConvertData convertData) {
        return format(num, convertData);
    }



    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static Short parseShort(String string, ConvertData convertData) throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string).shortValue();
        }
        return parse(string, convertData).shortValue();
    }

    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static Long parseLong(String string, ConvertData convertData) throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string).longValue();
        }
        return parse(string, convertData).longValue();
    }

    /**
     * parse Integer from string
     *
     * @param string          An integer read in string format
     * @param convertData Properties of the content read in
     * @return An integer converted from a string
     */
    public static Integer parseInteger(String string, ConvertData convertData) throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string).intValue();
        }
        return parse(string, convertData).intValue();
    }
    /**
     * parse Integer from string
     *
     * @param string          An integer read in string format
     * @param convertData Properties of the content read in
     * @return An integer converted from a string
     */
    public static BigInteger parseBigInteger(String string, ConvertData convertData) throws ParseException {

        return new BigInteger(string);
    }


    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static Float parseFloat(String string, ConvertData convertData) throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string).floatValue();
        }
        return parse(string, convertData).floatValue();
    }

    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static BigDecimal parseBigDecimal(String string, ConvertData convertData)
        throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string);
        }
        return new BigDecimal(parse(string, convertData).toString());
    }

    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static Byte parseByte(String string, ConvertData convertData) throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string).byteValue();
        }
        return parse(string, convertData).byteValue();
    }
    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static Boolean parseBoolean(String string, ConvertData convertData) {
        if (!hasFormat(convertData)) {
            return new Boolean(string);
        }
        return parseBool(string, convertData);
    }

    private static Boolean parseBool(String string, ConvertData convertData) {
        if ("0".equals(string)){
            return Boolean.FALSE;
        }
        return Boolean.TRUE ;
    }

    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     */
    public static Double parseDouble(String string, ConvertData convertData) throws ParseException {
        if (!hasFormat(convertData)) {
            return new BigDecimal(string).doubleValue();
        }
        return parse(string, convertData).doubleValue();
    }



    private static boolean hasFormat(ConvertData convertData) {
        return convertData != null  && !StringUtils.isEmpty(convertData.getNumberFormat());
    }

    /**
     * parse
     *
     * @param string
     * @param convertData
     * @return
     * @throws ParseException
     */
    private static Number parse(String string, ConvertData convertData) throws ParseException {
        String format = convertData.getNumberFormat();
        RoundingMode roundingMode = convertData.getRoundingMode();
        DecimalFormat decimalFormat = new DecimalFormat(format);
        decimalFormat.setRoundingMode(roundingMode);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat.parse(string);
    }

}
