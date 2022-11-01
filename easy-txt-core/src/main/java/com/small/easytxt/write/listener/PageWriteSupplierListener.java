package com.small.easytxt.write.listener;

import com.small.easytxt.function.QueryPageList;

import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/9/8 23:53
 * @version: v1.0
 */
public class PageWriteSupplierListener<T> implements WriteListener<T> {


    private int pageNo ;
    private int pageSize ;
    private List<T>  list ;
    private QueryPageList<List<T>> queryPageList;

    public PageWriteSupplierListener(int pageNo, int pageSize ,QueryPageList<List<T>> queryPageList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize ;
        this.queryPageList = queryPageList;
    }
    @Override
    public List<T> lineInvoke() {
        //System.out.println("pageNo = "+ pageNo);
        list = queryPageList.query(pageNo, pageSize);
        return list;
    }


}
