package com.itdan.springdata_demo.test;

import com.itdan.springdata_demo.StartUpApplication;
import com.itdan.springdata_demo.dao.RolesDAO;
import com.itdan.springdata_demo.dao.UserDAO;
import com.itdan.springdata_demo.pojo.Menus;
import com.itdan.springdata_demo.pojo.Roles;
import com.itdan.springdata_demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUpApplication.class)
public class MyTest2 {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RolesDAO rolesDAO;


    @Test
    public void testDemo01 () throws Exception{
     //一对一测试
        //创建user对象
        User user=new User();
        user.setAge(18);
        user.setName("小明");
        user.setAdd("王府井");

        //创建roles对象
        Roles roles=new Roles();
        roles.setName("演员");
        //建立关系
        user.setRoles(roles);
       // roles.setUser(user);

        userDAO.save(user);

    }

    @Test
    public void testDemo02 () throws Exception{
        //一对多测试
        //创建user对象
        User user=new User();
        user.setAge(18);
        user.setName("小hong");
        user.setAdd("王府井");

        //创建roles对象
        Roles roles=new Roles();
        roles.setName("演员");
        //建立关系
        //roles.getUsers().add(user);
        user.setRoles(roles);
        //保持数据
        userDAO.save(user);

        }

    @Test
    public void testDemo03 () throws Exception {
    //多对多测试

        //创建roles对象
        Roles roles=new Roles();
        roles.setName("演员");
        //创建菜单对象
        Menus menus=new Menus();
        menus.setMenusname("xxx管理平台");
        menus.setMenusurl("xxxxx");
        menus.setFatherid(1);

        Menus menus1=new Menus();
        menus1.setMenusname("xxx网吧");
        menus1.setMenusurl("xxxxx");
        menus1.setFatherid(2);

        //建立关系
        roles.getMenus().add(menus);
        roles.getMenus().add(menus1);

        menus.getRoles().add(roles);
        menus1.getRoles().add(roles);
        rolesDAO.save(roles);

    }

}
