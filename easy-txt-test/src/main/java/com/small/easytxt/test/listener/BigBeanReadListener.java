package com.small.easytxt.test.listener;

import cn.hutool.core.util.StrUtil;
import com.small.easytxt.metadata.AbstractReadListener;
import com.small.easytxt.test.bean.BigBean;

import java.io.File;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/9 10:42
 * @version: v1.0
 */
public class BigBeanReadListener extends AbstractReadListener<BigBean> {

    public BigBeanReadListener() {

    }

    @Override
    public void lineInvoke(Integer rowNo, BigBean data) {
        String ot = StrUtil.format(" rowNo: {} BigBean : {} ", new Object[]{rowNo, data});
        System.out.println(ot);
    }

    @Override
    public void fileEnd(File file) {
        System.out.println("File read over : " + file.getAbsolutePath());
    }

}
