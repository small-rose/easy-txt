package com.small.easytxt.write.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.file.FileWriter;
import com.small.easytxt.annotation.format.DateFormatFiled;
import com.small.easytxt.annotation.format.NumberFormatFiled;
import com.small.easytxt.converter.ConvertData;
import com.small.easytxt.converter.factory.ConverterFactory;
import com.small.easytxt.converter.strategy.Converter;
import com.small.easytxt.exception.BeanConvertException;
import com.small.easytxt.executor.FileExecutor;
import com.small.easytxt.metadata.FileWriterContext;
import com.small.easytxt.utils.FileNameUtil;
import com.small.easytxt.utils.StringUtils;
import com.small.easytxt.write.listener.PageWriteSupplierListener;
import com.small.easytxt.write.listener.TxtSupplierListener;
import com.small.easytxt.write.listener.TxtWriteListener;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

public class DefaultFileWriteExecutor implements FileExecutor {

    private FileWriterContext fileWriterContext;

    public FileWriterContext getFileWriterContext() {
        return fileWriterContext;
    }

    public DefaultFileWriteExecutor(FileWriterContext fileWriterContext){
        this.fileWriterContext = fileWriterContext ;
    }

    /**
     * 由 supplierListener 负责查
     * 然后再写
     */
    @Override
    public void execute() {
        File file = fileWriterContext.getFile();
        String splitor = fileWriterContext.getSplitor();

        List<TxtSupplierListener> txtSupplierListenerList = fileWriterContext.getTxtSupplierListenerList();
        FileWriter fileWriter = new FileWriter(file);
        //System.out.println(readListenerList);
        // 调用函数式接口获取查询结果
        PageWriteSupplierListener supplierListener = (PageWriteSupplierListener) txtSupplierListenerList.get(0);
        List<Object> list = supplierListener.queryData();

        if (!CollectionUtil.isEmpty(list)) {
            final List<String> data = new ArrayList<>(list.size());
            list.stream().forEach(o -> {
                // 转成文件行
                String line = beanToLine(o, splitor);
                data.add(line);
            });
            //System.out.println("data = " + data);
            fileWriter.appendLines(data);
        }
    }

    @Override
    public void addBegin(File file) {
        if (fileWriterContext.isTmpBegin()){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.appendLines(Arrays.asList("BEG"));
        }
    }

    @Override
    public void addEnd(File file) {
        if (fileWriterContext.isTmpEnd()){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.appendLines(Arrays.asList("END"));
        }
    }

    /**
     * 只管写，不管查
     * @param <T>
     * @param data
     */
    @Override
    public <T> void execute(File file, List<T> data) {
        String splitor = fileWriterContext.getSplitor();
        FileWriter fileWriter = new FileWriter(file);
        if (!CollectionUtil.isEmpty(data)) {
            final List<String> dataTemp = new ArrayList<>(data.size());
            data.stream().forEach(o -> {
                String line = beanToLine(o, splitor);
                dataTemp.add(line);
            });
            fileWriter.appendLines(dataTemp);
        }
    }


    @Override
    public void delTemplate() {
        // 删除模板文件
        if (fileWriterContext.getFile().exists()){
            // 自定义文件明不删除，只删除带 random  和 order的
            String fileName = fileWriterContext.getFile().getName();
            if (fileName.contains("random") || fileName.contains("order")){
                fileWriterContext.getFile().delete();
            }
        }
    }

    public List<File> getFileNameList() {
        List<File> fileList = new ArrayList<>();
        List<String> fileNameList = null;
        File file = fileWriterContext.getFile();
        int fileRecordSize = fileWriterContext.getFileRecordSize();
        int countSize = fileWriterContext.getCountSize();
        String fileName = file.getName();
        // 默认不分文件
        int fileNum = 1 ;
        if (fileRecordSize!=-1  && countSize > fileRecordSize){
            fileNum = countSize % fileRecordSize ==0 ? (int)Math.ceil(countSize / fileRecordSize) : countSize / fileRecordSize +1 ;
            fileNameList = FileNameUtil.hanler(fileName, fileNum);
        }else{
            // 1个文件
            fileNameList = FileNameUtil.hanler(fileName, fileNum);
        }
        for (String tempName  :fileNameList){
            fileList.add(FileUtils.getFile(file.getParentFile(), tempName));
        }

        return fileList ;
    }




    private String beanToLine(Object o, String splitor) {
        Map<Integer, Field> beanFieldMap = fileWriterContext.getBeanFieldMap();
        Set<Integer> integers = beanFieldMap.keySet();

        String result = "";
        StringBuffer line = new StringBuffer("") ;
        for (Integer integer : integers) {
            Field field = beanFieldMap.get(integer);
            field.setAccessible(true);
            String columnVal =  null;
            Class<?> type = field.getType();
            //System.out.println(type.getName());

            Converter converter = ConverterFactory.getConverterByType(type);
            ConvertData convertData = getConvertData(field);
            try {
                convertData.setSource(field.get(o));

                columnVal = converter.convertToString(convertData);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fileWriterContext.isReplaceLingBreak()){
                columnVal = columnVal.replace("\n","").replace("\r","")
                        .replace(splitor, "-");
            }
            //columnVal = (String) field.get(o);
            line = line.append(columnVal).append(splitor);
        }
        if (fileWriterContext.isEndSplitor()){
            // （1）如以分隔符 " || "为行结束符（结尾有空格） "a || b || c || "
            result = line.toString().substring(0, line.length()-1);
        }else if (StringUtils.isNotBlank(fileWriterContext.getLineEndSysbol())){
            // （1）如以分隔符 " || "为行结束符（结尾有空格），移除最后一个分隔符，更换为 " ||"(结尾无空格)
            //  "a || b || c ||"
            result = line.toString().substring(0, line.lastIndexOf(splitor));
            result += fileWriterContext.getLineEndSysbol();
        }else{
            // 移除末尾的分割符 "a || b || c"
            result = line.toString().substring(0, line.lastIndexOf(splitor));
        }
        return result ;
    }


    private ConvertData getConvertData(Field field){
        ConvertData convertData = new ConvertData();
        DateFormatFiled dateFormatFiled = field.getAnnotation(DateFormatFiled.class);
        NumberFormatFiled numberFormatFiled = field.getAnnotation(NumberFormatFiled.class);
        if (dateFormatFiled !=null && StringUtils.isNotBlank(dateFormatFiled.value())) {
            convertData.setDateFormat(dateFormatFiled.value());
        }

        if (numberFormatFiled!=null && StringUtils.isNotBlank(numberFormatFiled.value()) ){
            convertData.setNumberFormat(numberFormatFiled.value());
        }
        return  convertData;
    }
}
