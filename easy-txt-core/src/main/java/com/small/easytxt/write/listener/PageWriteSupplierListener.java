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
public class PageWriteSupplierListener<T> implements TxtSupplierListener<T> {


    private int pageNo;
    private int pageSize;
    private List<T> list;
    private QueryPageList<List<T>> queryPageList;

    /**
     * 分页查询监听器构造函数
     *
     * @param pageNo        页码，从1开始计数
     * @param pageSize      每页显示的记录数
     * @param queryPageList 分页查询接口，用于执行实际的分页查询操作
     */
    public PageWriteSupplierListener(int pageNo, int pageSize, QueryPageList<List<T>> queryPageList) {
        // 初始化页码
        this.pageNo = pageNo;
        // 初始化每页显示的记录数
        this.pageSize = pageSize;
        // 初始化分页查询接口
        this.queryPageList = queryPageList;
    }


    @Override
    /**
     * 查询数据的方法
     * @return 返回查询结果列表，列表中的元素类型为泛型T
     */
    public List<T> queryData() {
        // 打印当前页码（已注释）
        //System.out.println("pageNo = "+ pageNo);
        // 调用分页查询方法获取当前页的数据列表
        list = queryPageList.query(pageNo, pageSize);
        // 返回查询结果列表
        return list;
    }


}
