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
    //调用DiningTableDao，来完成复杂业务的逻辑处理
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

    /**
     * 查询指定id的一个餐桌信息
     *
     * @param id 餐桌号
     * @return 返回查询到的餐桌信息，以DiningTable对象形式返回。如果返回null，则说明不存在该餐桌号的餐桌
     */
    public DiningTable getDiningTableById(int id) {
        DiningTable diningTable = dtt.querySingleRow("select * from diningTable where id=?", DiningTable.class, id);
        return diningTable;
    }

    /**
     * 预订餐桌，预订失败则对表无修改，预订成功则更新餐桌状态、预定人名称、预定人手机号。
     *
     * @param id        预订的餐桌号
     * @param orderName 预订人名称
     * @param orderTel  预定人手机号
     * @return 返回预订是否成功。true代表预订成功，false代表预订失败。
     */
    public boolean bookTable(int id, String orderName, String orderTel) {
        int affectedRows = dtt.update("update diningTable set state='已预定',orderName=?,orderTel=? where id=?", orderName, orderTel, id);
        return affectedRows > 0;
    }

    /**
     * 更新餐桌状态
     *
     * @param id    餐桌id
     * @param state 需要更新到的餐桌状态
     * @return 返回是否更新成功，true为成功，false为失败
     */
    public boolean updateDiningTableState(Integer id, String state) {
        int affectedRows = dtt.update("update diningTable set state=? where id=?", state, id);
        return affectedRows > 0;
    }

    /**
     * 将指定餐桌的状态改为空闲状态，并且将预订人名称、电话置空
     *
     * @param id 餐桌id
     * @return 返回是否更新成功，true为成功，false为失败
     */
    public boolean updateDiningTableToFree(Integer id) {
        int affectedRows = dtt.update("update diningTable set state='空闲',orderName='',orderTel='' where id=?", id);
        return affectedRows > 0;
    }
}
