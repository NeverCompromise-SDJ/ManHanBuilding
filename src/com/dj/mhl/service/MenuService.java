package com.dj.mhl.service;

import com.dj.mhl.dao.MenuDao;
import com.dj.mhl.domain.Menu;

import java.util.List;

public class MenuService {
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
}
