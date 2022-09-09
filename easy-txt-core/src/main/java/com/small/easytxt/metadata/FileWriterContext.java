package com.small.easytxt.metadata;

import com.small.easytxt.write.listener.WriteListener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ FileReaderContext ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 15:46
 * @Version ： 1.0
 **/
public abstract class FileWriterContext {

    protected File file  ;
    protected String splitor ;
    protected Class object ;
    protected Object objectInstance ;
    protected boolean isToBean ;
    protected List<WriteListener> writeListenerList ;
    protected Map<Integer, Field> beanFieldMap ;

    FileWriterContext(){
        this.writeListenerList = new ArrayList<>();
    }

    public void registerWriteListener(WriteListener readListener) {
        writeListenerList.add(readListener);
    }

    public abstract void file(File file);

    public abstract File getFile();

    public abstract void split(String splitor);

    public abstract String getSplitor();

    public abstract void bean(Class head);

    public abstract Object getBean();

    public abstract List<WriteListener> getWriteListenerList();

    public abstract Map<Integer, Field> getBeanFieldMap();


    public abstract boolean isToBean();


}
