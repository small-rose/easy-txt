package com.small.easytxt.metadata;

import cn.hutool.core.lang.Assert;
import com.small.easytxt.listener.ReadListener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ AbstractFileReaderContext ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/8 16:55
 * @Version ： 1.0
 **/
public abstract class AbstractFileReaderContext extends FileReaderContext {


    @Override
    public void file(File file) {
        Assert.isTrue(file!=null, "文件不允许为空");
        Assert.isTrue(file.exists(), "找不到文件 ".concat(file.getAbsolutePath()));
        this.file = file;
    }

    @Override
    public void split(String splitor) {
        if (object!=null) {
            Assert.isTrue(splitor != null, "设置Class参数必须有行分隔符");
        }
        this.splitor = splitor;
    }

    @Override
    public File getFile() {
        return file ;
    }

    @Override
    public String getSplitor() {
        return splitor;
    }

    @Override
    public void bean(Class bean) {
        this.object = bean ;
    }


    @Override
    public Object getBean() {
        return object;
    }

    @Override
    public List<ReadListener> getReadListenerList() {
        return readListenerList;
    }

    @Override
    public Map<Integer, Field> getBeanFieldMap() {
        return beanFieldMap;
    }

    @Override
    public boolean isToBean() {
        return isToBean;
    }
}
