package com.small.easytxt.write.listener;

import java.util.List;

public interface TxtSupplierListener<T> {


    /**
     * 写行
     */
    List<T> queryData();
}
