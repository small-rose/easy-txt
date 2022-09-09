package com.small.easytxt.executor;

import cn.hutool.core.util.StrUtil;
import com.small.easytxt.annotation.TxtFiled;
import com.small.easytxt.annotation.TxtPorperty;
import com.small.easytxt.listener.ReadListener;
import com.small.easytxt.metadata.FileReaderContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ DefaultFileReadExecutor ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/8 11:14
 * @Version ： 1.0
 **/

public class DefaultFileReadExecutor implements FileReadExecutor{

    FileReaderContext fileReaderContext;

    public DefaultFileReadExecutor(FileReaderContext fileReaderContext){
        this.fileReaderContext = fileReaderContext ;
    }
    @Override
    public void execute()  throws IOException {
        File file = fileReaderContext.getFile();
        String splitor = fileReaderContext.getSplitor();
        List<ReadListener> readListenerList = fileReaderContext.getReadListenerList();
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            int lineNo = 1 ;
            while (it.hasNext()) {

                String line = it.nextLine();


                for (ReadListener listener : readListenerList) {
                    if (fileReaderContext.isToBean()){
                        Object object = lineToBean(line.split(splitor), fileReaderContext.getBeanFieldMap());
                        listener.lineInvoke(lineNo++, object);
                    }else {
                        listener.lineInvoke(lineNo++, line);
                    }
                }
            }
        } finally {
            it.close();
            for (ReadListener listener : readListenerList){
                listener.fileEnd(file);
            }
        }
    }

    private Object lineToBean(String[] data, Map<Integer, Field> beanFieldMap) {
        Class<?> beanClazz = (Class<?>) fileReaderContext.getBean();
        Object object = null;
        boolean fixIndex = true ;
        //System.out.println(beanClazz.getName());
        TxtPorperty txtPorperty = beanClazz.getAnnotation(TxtPorperty.class);
        if (txtPorperty !=null ){
            //System.out.println(txtPorperty.fixIndex());
            fixIndex = txtPorperty.fixIndex();
        }
        //System.out.println("fixIndex = " +fixIndex);
        try {
            object = beanClazz.newInstance();
            Set<Integer> indexSet = beanFieldMap.keySet();
            int index = 0 ;
            for (Integer integer : indexSet) {
                Field field = beanFieldMap.get(integer);
                field.setAccessible(true);
                Class<?> type = field.getType();

                String columnVal =  null;
                TxtFiled txtFiled = field.getAnnotation(TxtFiled.class);
                if (fixIndex){
                    if (txtFiled.index() >= data.length){
                        break;
                    }
                    columnVal = data[txtFiled.index()];
                }else{
                    if (index == data.length){
                        break;
                    }
                    columnVal = data[index++];
                }

                Object realData = convertType(type, columnVal);
                field.set(object, realData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    private Object convertType(Class<?> type, String data) {
        if (StrUtil.isBlank(data)){
            data = null;
        }

        switch (type.getSimpleName()){
            case "int":
                return data==null ? 0 : Integer.valueOf(data) ;
            case "Integer":
                return data==null ? data : Integer.valueOf(data) ;
            case "long":
                return data==null ? 0 : Long.valueOf(data) ;
            case "Long":
                return data==null ? data : Long.valueOf(data) ;
            case "BigDecimal":
                return data==null ? data : new BigDecimal(data) ;
            default:
                return data ;

        }
    }
}
