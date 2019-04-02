package com.itdan.springdata_demo.test;

import com.itdan.springdata_demo.StartUpApplication;
import com.itdan.springdata_demo.dao.ProductRepository;
import com.itdan.springdata_demo.dao.UserDAO;
import com.itdan.springdata_demo.pojo.Product;
import com.itdan.springdata_demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUpApplication.class)
public class MyTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductRepository productRepository;


    @org.junit.Test
    public void testAddUser () throws Exception{
        //添加用户测试
        User user=new User();
        user.setAge(18);
        user.setName("小明");
        user.setAdd("王府井");
        userDAO.save(user);
    }

    @Test
    public void testFindUser () throws Exception{
    }


    @Test
    public void testAddProduct () throws Exception{
     //添加商品
        Product product=new Product();
        product.setId("1234566");
        product.setName("product_demo1");
        product.setStatus("AUDITING");
        product.setThresholdAmount(new BigDecimal(10000));
        product.setStepAmount(new BigDecimal(0));
        product.setLockTerm(3);
        product.setRewardRate(new BigDecimal(0.03));
        product.setMemo("莫氏空翻机顶盒个好人");
        productRepository.save(product);
    }

}
