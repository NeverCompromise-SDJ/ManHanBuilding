package com.dj.mhl.service;

import com.dj.mhl.dao.BillDao;
import com.dj.mhl.domain.Menu;

import java.util.UUID;

/**
 * Bill的Service类，处理账单的相关逻辑
 *
 * @author SongDongJie
 * @create 2024/1/7 - 17:45
 */
public class BillService {
    //调用BillDao，来完成复杂的业务逻辑
    private BillDao bd = new BillDao();
    //调用MenuService的方法，来完成多表的协同需求
    private MenuService ms = new MenuService();
    //调用DiningTableService的方法，来完成多表的协同需求
    private DiningTableService dts = new DiningTableService();

    /**
     * 点餐：1.生成订单 2.更新餐桌状态
     * 其实这里应该加个事务，但是底层设计貌似加不上去
     *
     * @param menuId        菜品ID
     * @param nums          菜品数量
     * @param diningTableId 餐桌ID
     * @return 返回是否点餐成功（订单是否生成），true为成功，false为失败
     */
    public boolean orderMenu(Integer menuId, Integer nums, Integer diningTableId) {
        //1.生成订单
        //通过UUID类的方法，生成一个UUID，作为账单号。
        String billId = UUID.randomUUID().toString();
        //通过MenuId，得到对应菜品的单价，然后乘以数量得到菜品的总价
        Double moneyByMenuId = ms.getMenuById(menuId).getPrice() * nums;
        int affectedRows = bd.update("insert into bill(billId,menuId,nums,money,diningTableId,billDate,state) " +
                "values(?,?,?,?,?,now(),'未结账')", billId, menuId, nums, moneyByMenuId, diningTableId);
        if (affectedRows == 0) {//生成订单失败
            return false;
        }
        //2.更新餐桌状态
        boolean isUpdateSuccess = dts.updateDiningTableState(diningTableId, "就餐中");
        if (!isUpdateSuccess) {//更新餐桌状态失败
            return false;
        }
        return true;
    }
}
