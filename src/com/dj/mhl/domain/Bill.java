package com.dj.mhl.domain;

import java.util.Date;

/**
 * bill表的JavaBean类
 *
 * @author SongDongJie
 * @create 2024/1/6 - 22:14
 */
public class Bill {
    private Integer key;//自增主键
    private String billId;//账单号，可以按照在自己规则生成UUID
    private Integer menuId;//菜品编号，这里可以设计外键，我这里没有设计
    private Integer nums;//菜品的份数
    private Double money;//菜品金额
    private Integer diningTableId;//餐桌编号，同样可以设计外键
    private Date billDate;//订单日期
    private String state;//菜品的支付状态，分别有：‘未结账’，‘已经结账’，‘挂单’，‘现金’，‘支付宝’，‘微信’，‘坏账（吃霸王餐）’

    public Bill() {//反射需要
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "key=" + key +
                ", billId='" + billId + '\'' +
                ", menuId=" + menuId +
                ", nums=" + nums +
                ", money=" + money +
                ", diningTableId=" + diningTableId +
                ", billDate=" + billDate +
                ", state='" + state + '\'' +
                '}';
    }
}
