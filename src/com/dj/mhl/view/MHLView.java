package com.dj.mhl.view;

import com.dj.mhl.domain.DiningTable;
import com.dj.mhl.domain.Employee;
import com.dj.mhl.service.DiningTableService;
import com.dj.mhl.service.EmployeeService;
import com.dj.mhl.utils.Utility;

import java.util.List;
import java.util.Scanner;

/**
 * @author SongDongJie
 * @create 2024/1/1 - 22:09
 * 满汉楼主界面
 */
public class MHLView {
    //控制是否退出菜单
    private boolean loop = true;
    //接收用户的输入
    private String choose = "";
    //用于执行Employee表相关的业务
    private EmployeeService es = new EmployeeService();
    //用于执行DiningTable表相关的业务
    private DiningTableService dts = new DiningTableService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("===========满汉楼===========");
            System.out.println("\t\t1 登录满汉楼");
            System.out.println("\t\t2 退出满汉楼");
            System.out.println("请输入您的选择：");
            choose = Utility.readString(1);
            switch (choose) {
                case "1":
                    System.out.println("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.println("请输入密码：");
                    String empPwd = Utility.readString(50);
                    //根据员工号和密码，查询是否有该员工，返回一个Employee对象
                    Employee employee = es.getEmployeeByEmpIdAndPwd(empId, empPwd);
                    //如果的确查询到该员工，则进入员工的二级菜单
                    if (employee != null) {
                        System.out.println("===========登录成功[" + employee.getName() + "]===========");
                        while (loop) {
                            System.out.println("===========满汉楼(二级菜单)===========");
                            System.out.println("\t\t1 显示餐桌状态");
                            System.out.println("\t\t2 预定餐桌");
                            System.out.println("\t\t3 显示所有菜品");
                            System.out.println("\t\t4 点餐服务");
                            System.out.println("\t\t5 查看账单");
                            System.out.println("\t\t6 结账");
                            System.out.println("\t\t9 退出满汉楼");
                            System.out.println("请输入您的选择：");
                            choose = Utility.readString(1);
                            switch (choose) {
                                case "1":
                                    showDiningTableStatus();
                                    break;
                                case "2":
                                    System.out.println("预定餐桌");
                                    break;
                                case "3":
                                    System.out.println("显示所有菜品");
                                    break;
                                case "4":
                                    System.out.println("点餐服务");
                                    break;
                                case "5":
                                    System.out.println("查看账单");
                                    break;
                                case "6":
                                    System.out.println("结账");
                                    break;
                                case "9":
                                    loop = false;//退出循环
                                    break;
                                default:
                                    System.out.println("您的输入有误，请重新输入");
                                    break;
                            }
                        }
                    } else {//否则登陆失败
                        System.out.println("===========登陆失败===========");
                    }
                    break;
                case "2":
                    loop = false;//退出循环
                    break;
                default:
                    System.out.println("您的输入有误，请重新输入");
                    break;
            }
        }
        System.out.println("您已退出满汉楼系统");
    }

    /**
     * 显示所有的餐桌状态信息
     */
    private void showDiningTableStatus() {
        //拿到所有的餐桌状态信息（id和status属性有值就行）
        List<DiningTable> diningTableMsg = dts.getDiningTableStatus();
        //遍历显示
        System.out.println("餐桌编号\t餐桌状态");
        for (DiningTable diningTable : diningTableMsg) {
            System.out.println(diningTable.getId() + "\t\t" + diningTable.getState());
        }
    }

}
