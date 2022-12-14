package com.small.easytxt;

import cn.hutool.core.util.StrUtil;
import com.small.easytxt.annotation.TxtFiled;
import com.small.easytxt.converter.ConverterHelper;
import com.small.easytxt.exception.FiledIndexException;
import com.small.easytxt.metadata.AbstractFileReader;
import com.small.easytxt.read.executor.DefaultFileReadExecutor;

import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * @Project : easy-txt
 * @Author : zhangzongyuan
 * @Description : [ TxtFileReader ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2022/9/5 10:56
 * @Version ： 1.0
 **/
public class TxtFileReader extends AbstractFileReader {

    private DefaultFileReadExecutor  fileReadExecutor ;



    public void doRead()  {
        initCompenent();

        if (object!=null && object instanceof Class && StrUtil.isNotBlank(splitor)){
            this.isToBean = true ;
            initBean((Class) object);
        }
        ConverterHelper.initConverters(beanFieldMap);
        fileReadExecutor.execute() ;

    }

    private void initCompenent() {
        fileReadExecutor = new DefaultFileReadExecutor(this);
     }

    private void initBean(Class object) {
        try {

            objectInstance = object.newInstance();
            Field[] fields = objectInstance.getClass().getDeclaredFields();
            beanFieldMap = new TreeMap<Integer, Field>();
            for (Field field : fields){
                TxtFiled annotation = field.getAnnotation(TxtFiled.class);
                if (beanFieldMap.keySet().contains(annotation.index())){
                    throw new FiledIndexException("Duplicate indexes were found, please ensure that the index property values of the TxtFiled annotations in the bean ["+object.getName()+"] are unique.");
                }
                beanFieldMap.put(annotation.index(), field);
            }
            //System.out.println(beanFieldMap);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }



}
