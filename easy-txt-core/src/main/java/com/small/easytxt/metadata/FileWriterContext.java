package com.small.easytxt.metadata;

import com.small.easytxt.write.listener.TxtSupplierListener;
import com.small.easytxt.write.listener.TxtWriteListener;
import org.springframework.jdbc.core.JdbcTemplate;

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
    protected static final String COUNT_SQL = "select count(*) from ( ${SQL} )" ;
    protected static final Integer BATCH_WRITE_SIZE = 1000 ; ;

    protected File file  ;
    protected String splitor ;
    protected Class object ;
    protected Object objectInstance ;
    protected boolean isToBean ;
    protected List<TxtSupplierListener> txtSupplierListenerList ;
    protected List<TxtWriteListener> txtWriteListenerList ;
    protected Map<Integer, Field> beanFieldMap ;
    protected Map<String, Class<?>> beanFieldTypeMap ;

    protected String sql ;
    protected String sqlId ;
    protected int countSize ;
    protected int fetchSize ;
    protected JdbcTemplate jdbcTemplate;
    protected List params ;
    protected int fileRecordSize ;
    protected List<File> fileList ;
    protected String fileNameReg ;

    /**
     * 文件的 首行  尾行 是否保护 begin end
     */
    protected boolean beginEnd ;
    protected boolean tmpBegin;
    protected boolean tmpEnd;

    /**
     * 一行的末尾是否以分隔符结尾
     * 默认是 TRUE
     */
    protected boolean endSplitor = Boolean.TRUE ;

    /**
     * 一行的末尾可以自定义结束符
     *  默认是 ”“ 使用自定义结束符的前提的将 endSplitor 设置为 FALSE
     */
    protected String lineEndSysbol = "" ;


    /**
     * 数据列是否要替换 \r \n 换行符，并且数据中有分隔符时，替换成 -
     *  核销数据下发会使用
     */
    protected boolean replaceLingBreak = Boolean.FALSE ;


    FileWriterContext(){
        this.txtSupplierListenerList = new ArrayList<>();
        this.txtWriteListenerList = new ArrayList<>();
    }

    public void registerTxtSupplierListener(TxtSupplierListener writeListener) {
        txtSupplierListenerList.add(writeListener);
    }

    public void registerTxtWriteListener(TxtWriteListener writeListener) {
        txtWriteListenerList.add(writeListener);
    }

    public abstract void file(File file);

    protected abstract void sql(String sql);

    public abstract String getSql();

    protected abstract void setSqlId(String sql);

    public abstract String getSqlId();

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    protected abstract int getFetchSize();

    public abstract void setFetchSize(int fetchSize) ;

    public abstract int getFileRecordSize();

    protected abstract void setFileRecordSize(int fileRecordSize);

    public abstract File getFile();

    public abstract void split(String splitor);

    public abstract String getSplitor();

    public abstract void bean(Class head);

    protected abstract Object getBean();

    public abstract List<TxtSupplierListener> getTxtSupplierListenerList();

    public abstract List<TxtWriteListener> getTxtWriteListenerList();

    public abstract Map<Integer, Field> getBeanFieldMap();


    protected abstract boolean isToBean();

    public abstract int getCountSize();

    protected abstract void setCountSize(int countSize);

    public abstract List<File> getFileList();

    protected abstract void setFileList(List<File> fileList);

    protected abstract String getFileNameReg();

    protected abstract void setFileNameReg(String fileNameReg);

    protected abstract boolean isBeginEnd();
    public abstract void setBeginEnd(boolean beginEnd);

    public abstract boolean isTmpBegin();
    protected abstract void setTmpBegin(boolean tmpBegin);

    public abstract boolean isTmpEnd();
    protected abstract void setTmpEnd(boolean tmpEnd);


    public abstract boolean isEndSplitor();
    public abstract void setEndSplitor(boolean endSplitor);


    public abstract String getLineEndSysbol();
    public abstract void setLineEndSysbol(String lineEndSysbol);


    public abstract boolean isReplaceLingBreak();
    public abstract void setReplaceLingBreak(String replaceLineBr);




}
