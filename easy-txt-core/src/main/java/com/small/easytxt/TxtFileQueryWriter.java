package com.small.easytxt;


import com.small.easytxt.exception.SQLQueryException;
import com.small.easytxt.metadata.AbstractFileWriter;
import com.small.easytxt.utils.CamelCaseUtils;
import com.small.easytxt.utils.DateUtils;
import com.small.easytxt.utils.MemoryUtils;
import com.small.easytxt.write.executor.DefaultFileWriteExecutor;
import com.small.easytxt.write.listener.TxtWriteListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Project : easy-txt
 * @Author : small-rose
 * @Description : [ TxtFileReader ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
@Slf4j
public class TxtFileQueryWriter extends AbstractFileWriter {

    private static Logger logger = LoggerFactory.getLogger(TxtFileQueryWriter.class);

    private DefaultFileWriteExecutor fileWriteExecutor;

    public <T> void doWrite(String sql, Class<T> object, Object... params) {
        fileWriteExecutor = new DefaultFileWriteExecutor(this);
        prepare(object);

        List<TxtWriteListener> writeListenerList = getTxtWriteListenerList();

        final List<T> result = new ArrayList<>();

        String countsql = COUNT_SQL.replace("\\$\\{SQL}", sql);
        jdbcTemplate.setQueryTimeout(-1);
        Integer aLong = jdbcTemplate.queryForObject(countsql, Integer.class);
        if (ObjectUtils.isEmpty(aLong) || aLong<=0 ){
            log.warn("count sql query result is 0 , no file will be written , do nothing !");
            return;
        }
        setCountSize(aLong);

        // 查到数据可以写
        List<File> fileList = fileWriteExecutor.getFileNameList();
        AtomicInteger pageNum = new AtomicInteger(0);
        AtomicInteger totalWritten = new AtomicInteger(0);
        AtomicReference<File> currentFile = new AtomicReference(0);


        getJdbcTemplate().setFetchSize(getFetchSize());
        getJdbcTemplate().query(sql, rs -> {
            try {
                if (totalWritten.get()==0){
                    currentFile.set(fileList.get(pageNum.get()));
                    // 写首行的BEGIN
                    setTmpBegin(true);
                    fileWriteExecutor.addBegin(currentFile.get());
                    setTmpBegin(false);

                    log.info("current file : {} ， path : {}", (pageNum.get()+1), currentFile.get().getAbsolutePath());
                }
                // 字段名称
                List<String> columnNames = new ArrayList<>();
                ResultSetMetaData meta = rs.getMetaData();
                int num = meta.getColumnCount();
                String cname = null;
                String property = null ;
                for (int i = 0; i < num; i++) {
                    // table.column形式的字段去掉前缀table.
                    cname = resolveColumn(meta.getColumnLabel(i + 1 ));
                    // 下划线转驼峰
                    property = CamelCaseUtils.toCamelCase(cname);
                    columnNames.add(property);
                }
                int N = 0;
                // 设置值
                HashMap<String, Object> row = null;
                Object value = null ;
                String columnName = null;
                while (rs.next()) {
                    row = new HashMap(num);
                    T obj = (T) object.getConstructor().newInstance();
                    for (int i = 0; i < num; i++) {
                        // 获取值
                        value = rs.getObject(i + 1);
                        // table.column形式的字段去掉前缀table.
                        columnName = columnNames.get(i);
                        //BeanUtils.copyProperty(obj, property, value);
                        value = checkBeanType(columnName, value);
                        row.put(columnName, value);
                    }
                    // 注册转换器，处理 null 值
                    ConvertUtils.register(new DateConverter(null), Date.class);
                    ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
                    BeanUtils.populate(obj, row);
                    // 过滤自定义规则数据
                    if (writeListenerList.size()>0 && !writeListenerList.get(0).invokeLine(obj)){
                        continue;
                    }
                    result.add(obj);


                    if (result.size() >= BATCH_WRITE_SIZE) {
                        fileWriteExecutor.execute(currentFile.get(), result);
                        totalWritten.addAndGet(result.size());
                        result.clear();
                        log.info(" bath writting : {} file {}");
                        long totalMemory = MemoryUtils.getTotalMemory(result);

                    }

                    if (getFileRecordSize()!=-1 && result.size() >= (getFileRecordSize()-totalWritten.get())){
                        fileWriteExecutor.execute(currentFile.get(), result);
                        //
                        totalWritten.addAndGet(result.size());
                        result.clear();
                        log.info(" bath writting end : {} file {}");
                    }
                }

                if (result.size() > 0) {
                    // 尾部数据
                    fileWriteExecutor.execute(currentFile.get(), result);
                }
                if (getFileRecordSize()==-1 || result.size()>0){

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLQueryException("查询报错了");
            }finally {
                if (writeListenerList.size()>0 ) {
                    // 写文件结束
                    writeListenerList.get(0).doAfterWrite(fileWriteExecutor.getFileWriterContext());
                }

            }
        }, params);
    }


    private Object checkBeanType(String name, Object value){
        String className = "";
        if (ObjectUtils.isEmpty(value)){
            return null;
        }
        Class valType = value!=null ? value.getClass() : null;
        if (valType==null){
            return value;
        }
        className = getBean().getClass().getTypeName();
        Class<?> targetType = beanFieldTypeMap.get(name);
        logger.info("valType :"+valType +" .targetType : "+targetType);
        if (targetType == null) {
            throw new RuntimeException("导出的sql查询结果列映射对象"+className+"缺少对于的属性"+name+"声明");
        }
        if (valType.getTypeName().equalsIgnoreCase(targetType.getTypeName())){
            return value;
        }
        if (value instanceof Timestamp){
            switch (targetType.getTypeName()){
                case "java.util.Date":
                    return new Date(((Timestamp)value).getTime());
                case "java.sql.Date":
                    return new java.sql.Date(((Timestamp)value).getTime());
                case "java.time.LocalDate":
                    return ((Timestamp)value).toLocalDateTime().toLocalDate();
                case "java.time.LocalDateTime":
                    return ((Timestamp)value).toLocalDateTime();
            }
        }else{
            if ("java.lang.String".equals(targetType.getTypeName())){
                String  conVal = "";
                switch (valType.getTypeName()){
                    case "java.sql.Timestamp":
                        return DateUtils.format(new Date(((Timestamp)value).getTime()));
                    case "java.sql.Date":
                        return DateUtils.format(new java.sql.Date(((Timestamp)value).getTime()));
                    case "java.math.BigDecimal":
                        return String.valueOf(value);
                    default:
                        conVal = String.valueOf(value);
                }
                return conVal ;
            }
            if ("java.math.BigDecimal".equals(targetType.getTypeName()) && !"java.math.BigDecimal".equals(valType.getTypeName())) {
                return new BigDecimal(String.valueOf(value));
            }
            log.info("接收对象"+className+"."+name+"的目标类型是："+targetType.getTypeName()+", sql查询取到的类型是"+valType.getTypeName());
            throw new RuntimeException("接收对象"+className+"."+name+"的目标类型是："+targetType.getTypeName()+", sql查询取到的类型是"+valType.getTypeName());
        }
        return value;
    }


    private String resolveColumn(String column) {
        final int notExistIndex = -1;
        int index = column.indexOf(".");
        if (index == notExistIndex) {
            return column;
        }
        return column.substring(index + 1);
    }



}
