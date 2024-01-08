package com.dj.mhl.domain;

import java.sql.Timestamp;

/**
 * 这是一个javabean，可以和多个表的字段进行对应，以此来进行多表查询。
 *
 * @author SongDongJie
 * @create 2024/1/8 - 23:47
 */
public class MultiTableBean {
    private Integer id;//bill表属性，自增主键
    private String billId;//bill表属性，账单号，可以按照在自己规则生成UUID
    private Integer menuId;//bill表属性，菜品编号，这里可以设计外键，我这里没有设计
    private Integer nums;//bill表属性，菜品的份数
    private Double money;//bill表属性，菜品金额
    private Integer diningTableId;//bill表属性，餐桌编号，同样可以设计外键
    private Timestamp billDate;//bill表属性，订单日期
    private String state;//bill表属性，菜品的支付状态，分别有：‘未结账’，‘已经结账’，‘挂单’，‘现金’，‘支付宝’，‘微信’，‘坏账（吃霸王餐）’
    private String name;//menu表属性，菜谱名称

    public MultiTableBean() {//反射需要

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getBillDate() {
        return billDate;
    }

    public void setBillDate(Timestamp billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
