package com.small.easytxt.write.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.file.FileWriter;
import com.small.easytxt.executor.FileExecutor;
import com.small.easytxt.metadata.FileWriterContext;
import com.small.easytxt.write.listener.WriteListener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
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

    public DefaultFileWriteExecutor(FileWriterContext fileWriterContext){
        this.fileWriterContext = fileWriterContext ;
    }
    @Override
    public void execute()  {
        File file = fileWriterContext.getFile();
        String splitor = fileWriterContext.getSplitor();
        List<WriteListener> readListenerList = fileWriterContext.getWriteListenerList();

        //System.out.println(readListenerList);
        List<Object> list = null;
        for (WriteListener writeListener : readListenerList) {
            list = writeListener.lineInvoke();
            //System.out.println(list);
        }

        FileWriter fileWriter = new FileWriter(file);
        if (!CollectionUtil.isEmpty(list)){
            final List<String> data = new ArrayList<>(list.size());
            list.stream().forEach(o->{
                String line = beanToLine(o, splitor);

                data.add(line);
            });
            //System.out.println("data = " + data);
            fileWriter.appendLines(data);

        }

    }

    private String beanToLine(Object o, String splitor) {
        Map<Integer, Field> beanFieldMap = fileWriterContext.getBeanFieldMap();
        Set<Integer> integers = beanFieldMap.keySet();

        String line = "" ;
        for (Integer integer : integers) {
            Field field = beanFieldMap.get(integer);
            field.setAccessible(true);
            String columnVal =  null;

            try {
                columnVal = (String) field.get(o);
            } catch (IllegalAccessException e) {
                    e.printStackTrace();
            }
            line = line.concat(columnVal).concat(splitor);
        }
        line = line.substring(0, line.length()-1);
        return line;
    }


}
