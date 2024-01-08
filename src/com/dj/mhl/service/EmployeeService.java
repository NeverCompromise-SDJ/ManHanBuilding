package com.dj.mhl.service;

import com.dj.mhl.dao.EmployeeDao;
import com.dj.mhl.domain.Employee;

/**
 * 业务层，用于组织各个DAO类执行复杂的业务需求，如登录等。
 * 这里是调用EmployeeDao类来完成复杂的业务需求
 *
 * @author SongDongJie
 * @create 2024/1/2 - 23:37
 */
public class EmployeeService {
    //调用EmployeeDao，来完成Employee表相关业务的逻辑处理
    private EmployeeDao ed = new EmployeeDao();

    /**
     * 通过empId和pwd，返回一个Employee对象
     *
     * @param empId 员工号
     * @param pwd   密码
     * @return 返回查询到的Employee对象，如果为null则没有查询到该员工
     */
    public Employee getEmployeeByEmpIdAndPwd(String empId, String pwd) {
        Employee employee = ed.querySingleRow("select * from employee where empid=? and pwd=md5(?)", Employee.class, empId, pwd);
        return employee;
    }
}
