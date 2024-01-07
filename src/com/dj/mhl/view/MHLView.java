package com.dj.mhl.view;

import com.dj.mhl.domain.Bill;
import com.dj.mhl.domain.DiningTable;
import com.dj.mhl.domain.Employee;
import com.dj.mhl.domain.Menu;
import com.dj.mhl.service.BillService;
import com.dj.mhl.service.DiningTableService;
import com.dj.mhl.service.EmployeeService;
import com.dj.mhl.service.MenuService;
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
    //用于执行Menu表相关的业务
    private MenuService ms = new MenuService();
    //用于执行Bill表相关的业务
    private BillService bs = new BillService();

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
                                    bookDiningTable();
                                    break;
                                case "3":
                                    showMenuMsg();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    showAllBills();
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

    /**
     * 预订餐桌
     */
    private void bookDiningTable() {
        System.out.println("请选择要预定的餐桌编号(-1退出)：");
        int bookDiningTableId = Utility.readInt(1);
        if (bookDiningTableId == -1) {//退出预订
            System.out.println("===========取消预定餐桌===========");
            return;
        }
        System.out.println("确认是否预定(Y/N)：");
        char doubleEnsure = Utility.readConfirmSelection();
        if (doubleEnsure == 'N') {//第二次确认要退出预订
            System.out.println("===========取消预定餐桌===========");
            return;
        }
        //到了这里，可以确认用户是真的要预定餐桌。通过餐桌ID，查询该餐桌的信息
        DiningTable diningTable = dts.getDiningTableById(bookDiningTableId);
        if (diningTable == null) {//餐桌号不存在时
            System.out.println("===========该餐桌不存在===========");
            return;
        }
        if (!diningTable.getState().equals("空闲")) { //餐桌非空闲时
            System.out.println("===========该餐桌已经被预订或者就餐中===========");
            return;
        }
        System.out.println("请输入预定人名字：");
        String bookPersonName = Utility.readString(10);
        System.out.println("请输入预定人电话：");
        String bookPersonTel = Utility.readString(13);
        //到了这里，可以真正执行进行预订，返回预订后的结果
        boolean isBookSuccess = dts.bookTable(bookDiningTableId, bookPersonName, bookPersonTel);
        if (!isBookSuccess) {//预订失败
            System.out.println("===========预订失败===========");
            return;
        }
        System.out.println("===========预定" + bookDiningTableId + "号餐桌成功===========");
    }

    /**
     * 展示所有菜品的信息
     */
    private void showMenuMsg() {
        List<Menu> menuList = ms.getMenuMsg();
        System.out.printf("%-6s%-6s%-6s%-6s%n", "菜品编号", "菜品名", "类别", "价格");
        for (Menu menu : menuList) {
            System.out.printf("%-9s%-6s%-6s%-6s%n", menu.getId(), menu.getName(), menu.getType(), menu.getPrice());
        }
        System.out.println("===========显示完毕===========");
    }

    /**
     * 完成点餐
     */
    private void orderMenu() {
        System.out.println("===========点餐服务===========");
        System.out.println("请选择点餐的桌号(-1退出)：");
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {//取消点餐
            System.out.println("===========取消点餐===========");
            return;
        }
        if (dts.getDiningTableById(orderDiningTableId) == null) {//点餐的桌号不存在
            System.out.println("===========点餐的桌号不存在===========");
            return;
        }
        System.out.println("请选择菜品编号(-1退出)：");
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {//取消点餐
            System.out.println("===========取消点餐===========");
            return;
        }
        if (ms.getMenuById(orderMenuId) == null) {//点的菜品不存在
            System.out.println("===========点的菜品不存在===========");
            return;
        }
        System.out.println("请选择菜品数量(-1退出)：");
        int orderNums = Utility.readInt();
        if (orderNums == -1) {//取消点餐
            System.out.println("===========取消点餐===========");
            return;
        }
        if (orderNums <= 0) {
            System.out.println("===========点餐数量不正确，应大于0的整数===========");
            return;
        }
        System.out.println("请选择是否点这个菜(Y/N)：");
        char confirmOrder = Utility.readConfirmSelection();
        if (confirmOrder == 'N') {
            System.out.println("===========取消点餐===========");
            return;
        }
        //真正开始执行点餐操作
        boolean isOrderSuccess = bs.orderMenu(orderMenuId, orderNums, orderDiningTableId);
        if (!isOrderSuccess) {
            System.out.println("===========点餐失败===========");
            return;
        }
        System.out.println("===========点餐成功===========");
    }

    private void showAllBills() {
        List<Bill> billList = bs.getAllBills();
        System.out.printf("%-6s%-6s%-6s%-6s%-6s%-22s%s%n", "编号", "菜品号", "菜品数量", "金额", "桌号", "日期", "状态");
        for (Bill bill : billList) {
            System.out.printf("%-8s%-8s%-9s%-8s%-7s%-24s%s%n", bill.getId(), bill.getMenuId(), bill.getNums(),
                    bill.getMoney(), bill.getDiningTableId(), bill.getBillDate(), bill.getState());
        }
        System.out.println("===========显示完毕===========");
    }

}
