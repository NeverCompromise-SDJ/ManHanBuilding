package com.dj.mhl.domain;

/**
 * Employee表的POJO（JavaBean）类，是Employee表的映射
 *
 * @author SongDongJie
 * @create 2024/1/2 - 23:32
 */
public class Employee {
    private Integer id;
    private String empId;//员工号
    private String pwd;//密码
    private String name;//员工名
    private String job;//员工职位

    public Employee() {//Apache-Utils的反射需要
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empId='" + empId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
