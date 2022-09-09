package com.small.easytxt;

import cn.hutool.core.util.StrUtil;
import com.small.easytxt.annotation.TxtFiled;
import com.small.easytxt.executor.DefaultFileReadExecutor;
import com.small.easytxt.metadata.AbstractFileReaderContext;

import java.io.IOException;
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
public class TxtFileReader extends AbstractFileReaderContext {

    private DefaultFileReadExecutor  fileReadExecutor ;



    public void doRead() throws IOException {
        initCompenent();

        if (object!=null && object instanceof Class && StrUtil.isNotBlank(splitor)){
            this.isToBean = true ;
            initBean((Class) object);
        }

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
