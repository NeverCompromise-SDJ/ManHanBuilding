package com.dj.mhl.service;

import com.dj.mhl.dao.MenuDao;
import com.dj.mhl.domain.Menu;

import java.util.List;

public class MenuService {
    //调用MenuDao，来完成Menu表相关业务的逻辑处理
    private MenuDao md = new MenuDao();

    /**
     * 返回菜单里所有菜品的信息
     *
     * @return 以ArrayList<Menu>形式返回
     */
    public List<Menu> getMenuMsg() {
        List<Menu> menuList = md.queryMultiplyRow("select * from menu", Menu.class);
        return menuList;
    }


    /**
     * 通过菜品ID，得到对应的菜品信息
     *
     * @param id 菜品ID
     * @return 返回得到的单个菜品信息，没有对应菜品则返回null
     */
    public Menu getMenuById(Integer id) {
        Menu menu = md.querySingleRow("select * from menu where id=?", Menu.class, id);
        return menu;
    }
}
