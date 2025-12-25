package com.small.easytxt.utils;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : db-demo
 * @Author : zhangzongyuan
 * @Description : [ MemoryUtils ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2024/7/12 18:06
 * @Version ： 1.0
 **/

@Component
public class MemoryUtils {

    @PostConstruct
    public void  init(){
        System.setProperty("java.vm.name","Java HotSpot(TM) ");
    }
    /**
     * JDK自带内存计算工具
     * */
    public static long getTotalMemory(Object obj) {
        //System.setProperty("java.vm.name","Java HotSpot(TM) ");
        // openjdk 特有的
        //long totalMemory = ObjectSizeCalculator.getObjectSize(obj);
        long totalMemory = calculateRowObjectSize(obj);
        //System.out.println("getTotalMemory getObject memory : " + totalMemory);
        return totalMemory;
    }

    // 计算行对象的内存占用
    public static long calculateRowObjectSize(Object rowObject) {
        if (rowObject == null) {
            return 0;
        }

        Class<?> clazz = rowObject.getClass();
        long size = 16; // 对象头大小

        // 获取所有字段（包括父类字段）
        List<Field> fields = getAllFields(clazz);

        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                Class<?> fieldType = field.getType();
                size += getFieldTypeSize(fieldType);
            }
        }

        // 内存对齐
        return (size + 7) & ~7;
    }

    // 获取类及其父类的所有字段
    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    fields.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    // 获取字段类型大小
    private static long getFieldTypeSize(Class<?> type) {
        // 基本类型
        if (type == boolean.class || type == byte.class) {
            return 1;
        } else if (type == char.class || type == short.class) {
            return 2;
        } else if (type == int.class || type == float.class) {
            return 4;
        } else if (type == long.class || type == double.class) {
            return 8;
        }

        // 字符串特殊处理（估算）
        if (type == String.class) {
            return 24; // String对象头 + char数组引用 + 其他字段
        }

        // 其他引用类型
        return 8; // 64位JVM上引用大小
    }

}
