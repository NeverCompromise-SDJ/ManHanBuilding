package com.dj.mhl.domain;

/**
 * DiningTable表的POJO（JavaBean）类
 *
 * @author SongDongJie
 * @create 2024/1/3 - 22:54
 */
public class DiningTable {
    private Integer id;//餐桌编号
    private String state;//餐桌状态：‘空闲’，‘已预定’，‘就餐中’
    private String orderName;//预定人名字
    private String orderTel;//预定人电话

    public DiningTable() {//反射所需
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return "DiningTable{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderTel='" + orderTel + '\'' +
                '}';
    }
}
