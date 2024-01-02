package com.dj.mhl.view;

import com.dj.mhl.utils.Utility;
import org.junit.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

/**
 * @author SongDongJie
 * @create 2024/1/1 - 22:09
 */
public class MHLView {
    //控制是否退出菜单
    private boolean loop = true;
    //接收用户的输入
    private String choose = "";

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
                    if (empId.equals("123") && empPwd.equals("123")) {
                        System.out.println("登录成功");
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
                                    System.out.println("显示餐桌状态");
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
                    } else {
                        System.out.println("登陆失败");
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

}
