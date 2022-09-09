package com.small.easytxt.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/8 23:53
 * @version: v1.0
 */
public class PageReadConsumerListener<T> implements ReadListener<T> {

    private int pageSize ;
    private List<T>  list ;
    private Consumer<List<T>> consumer;

    public PageReadConsumerListener(int pageSize, Consumer<List<T>> consumer) {
        this.pageSize = pageSize;
        this.consumer = consumer;
        list = new ArrayList<>(pageSize);
    }
    @Override
    public void lineInvoke(Integer rowNo, T data) {
        list.add(data);
        if (list.size() >= pageSize) {
            consumer.accept(list);
            list.clear();
        }
    }

    @Override
    public void fileEnd(File file) {
        consumer.accept(list);
    }
}
