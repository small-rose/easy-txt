package com.small.easytxt.metadata;

import cn.hutool.core.lang.Assert;
import com.small.easytxt.annotation.TxtFiled;
import com.small.easytxt.exception.FiledIndexException;
import com.small.easytxt.write.listener.TxtSupplierListener;
import com.small.easytxt.write.listener.TxtWriteListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ AbstractFileReader ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/9 14:18
 * @Version ： 1.0
 **/
public abstract class AbstractFileWriter extends FileWriterContext{
    @Override
    public void file(File file) {
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
    public List<TxtSupplierListener> getTxtSupplierListenerList() {
        return txtSupplierListenerList;
    }

    @Override
    public List<TxtWriteListener> getTxtWriteListenerList() {
        return txtWriteListenerList;
    }
    @Override
    public Map<Integer, Field> getBeanFieldMap() {
        return beanFieldMap;
    }

    @Override
    public boolean isToBean() {
        return isToBean;
    }

    @Override
    protected void sql(String sql) {
        this.sql = sql ;
    }
    @Override
    public String getSql() {
        return this.sql;
    }

    @Override
    protected void setSqlId(String sqlId) {
        this.sqlId = sqlId ;
    }

    @Override
    public String getSqlId() {
        return this.sqlId;
    }
    @Override
    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getFetchSize() {
        return this.fetchSize;
    }

    @Override
    public void setFetchSize(int fetchSize) {
        this.fetchSize= fetchSize ;
    }

    @Override
    public int getFileRecordSize() {
        return fileRecordSize;
    }

    @Override
    public void setFileRecordSize(int fileRecordSize) {
        this.fileRecordSize=fileRecordSize;
    }

    @Override
    public int getCountSize() {
        return countSize;
    }

    @Override
    protected void setCountSize(int countSize) {
        this.countSize = countSize ;
    }

    @Override
    public List<File> getFileList() {
        return fileList;
    }

    @Override
    protected void setFileList(List<File> fileList) {

    }
    @Override
    protected String getFileNameReg() {
        return fileNameReg;
    }

    @Override
    protected void setFileNameReg(String fileNameReg) {
        this.fileNameReg = fileNameReg;
    }

    @Override
    protected boolean isBeginEnd() {
        return false;
    }

    @Override
    public void setBeginEnd(boolean beginEnd) {
        this.beginEnd = beginEnd ;
    }

    @Override
    public boolean isTmpBegin() {
        return false;
    }

    @Override
    public void setTmpBegin(boolean tmpBegin) {
        this.tmpBegin = tmpBegin && beginEnd ;
    }

    @Override
    public boolean isTmpEnd() {
        return false;
    }

    @Override
    protected void setTmpEnd(boolean tmpEnd) {
        this.tmpEnd = tmpEnd && beginEnd ;
    }

    /**
     *  一行的末尾是否以分隔符结尾
     * @return
     */
    @Override
    public boolean isEndSplitor() {
        return false;
    }
    @Override
    public void setEndSplitor(boolean endSplitor) {
        this.endSplitor = endSplitor ;
    }

    @Override
    public String getLineEndSysbol() {
        return lineEndSysbol;
    }

    @Override
    public void setLineEndSysbol(String lineEndSysbol) {
        this.lineEndSysbol =lineEndSysbol ;
    }

    @Override
    public boolean isReplaceLingBreak() {
        return false;
    }

    /**
     * 是否移除数据换行符
     * @param replaceLineBr
     */
    @Override
    public void setReplaceLingBreak(String replaceLineBr) {
        this.replaceLingBreak = replaceLingBreak ;
    }

    protected void prepare(Class obj) {

        if (obj!=null){
            bean(obj);
        }
        if (object != null && object instanceof Class && StringUtils.hasText(splitor)) {
            this.isToBean = true;
            initBean(object);
        }
        setSqlId("自己给个ID"+System.currentTimeMillis());
    }


    protected void initBean(Class object) {
        try {

            objectInstance = object.newInstance();
            Field[] fields = objectInstance.getClass().getDeclaredFields();
            beanFieldMap = new TreeMap<Integer, Field>();
            beanFieldTypeMap = new HashMap<>();
            for (Field field : fields){
                TxtFiled annotation = field.getAnnotation(TxtFiled.class);
                if (beanFieldMap.keySet().contains(annotation.index())){
                    throw new FiledIndexException("Duplicate indexes were found, please ensure that the index property values of the TxtFiled annotations in the bean ["+object.getName()+"] are unique.");
                }
                beanFieldMap.put(annotation.index(), field);
                beanFieldTypeMap.put(field.getName(), field.getType());
            }
            //System.out.println(beanFieldMap);
            if (beanFieldMap.size()<=0){
                throw new FiledIndexException("Not found "+object.getTypeName()+" TxtFiled annotation ! ");
            }
        } catch (InstantiationException | IllegalAccessException e) {
            //e.printStackTrace();
            throw new FiledIndexException("init bean field index error ", e);
        }
    }

}
