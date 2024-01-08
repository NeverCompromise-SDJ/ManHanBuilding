package com.dj.mhl.service;

import com.dj.mhl.dao.BillDao;
import com.dj.mhl.domain.Bill;

import java.util.List;
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

    /**
     * 返回所有账单信息
     *
     * @return 返回含有所有账单的一个ArrayList集合
     */
    public List<Bill> getAllBills() {
        List<Bill> billList = bd.queryMultiplyRow("select * from bill", Bill.class);
        return billList;
    }

    /**
     * 查询指定餐桌号的餐桌中，是否有未结账的订单
     *
     * @param diningTableId 餐桌号
     * @return 返回指定餐桌是否有未结账的订单，true为有未结账的订单，false为没有未结账的订单。
     */
    public boolean hasUncheckedBillsByDiningTableId(Integer diningTableId) {
        Bill bill = bd.querySingleRow("select * from bill where diningTableId=? and state='未结账' limit 0,1",
                Bill.class, diningTableId);//使用limit可以提高查询效率
        return bill != null;
    }

    /**
     * 结账前置条件：1.餐桌存在（View层判断） 2.该餐桌有未结账的账单（View层判断）
     * 结账步骤：1.更新bill表的state（只更新未结账的账单，不要修改其他状态的账单） 2.更新diningTable表的state
     *
     * @param diningTableId 结账的餐桌号
     * @param payMethod     结账的方式
     * @return 返回结账是否成功，true为成功，false为不成功
     */
    public boolean payBills(Integer diningTableId, String payMethod) {
        //更新bill表的state
        int affectedRows = bd.update("update bill set state=? where diningTableId=? and state='未结账'",
                payMethod, diningTableId);
        if (affectedRows == 0) {//更新失败
            return false;
        }
        //更新diningTable表的state
        boolean isUpdateSuccess = dts.updateDiningTableToFree(diningTableId);
        if (!isUpdateSuccess) {
            return false;
        }
        return true;
    }
}
