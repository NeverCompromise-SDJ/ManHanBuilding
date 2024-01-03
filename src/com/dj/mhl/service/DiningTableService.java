package com.dj.mhl.service;

import com.dj.mhl.dao.DiningTableDao;
import com.dj.mhl.domain.DiningTable;

import java.util.List;

/**
 * DiningTable的Service类
 *
 * @author SongDongJie
 * @create 2024/1/3 - 23:08
 */
public class DiningTableService {
    private DiningTableDao dtt = new DiningTableDao();

    /**
     * 返回所有餐桌的状态信息
     *
     * @return 以ArrayList<DiningTable>形式返回，其中DiningTable对象只有id和state被赋值了
     */
    public List<DiningTable> getDiningTableStatus() {
        List<DiningTable> diningTableList = dtt.queryMultiplyRow("select id,state from diningTable", DiningTable.class);
        return diningTableList;
    }
}
