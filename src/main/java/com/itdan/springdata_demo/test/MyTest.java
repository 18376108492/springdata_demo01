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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

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
        //根据name查询相应的User对象
        //方法明名规则
        //findBy（关键词）
        //判断相等的三种形式,1.什么都不写默认就是相等;2.Is;3.Equals
       List<User> users= userDAO.findByNameIs("小明");
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testThan () throws Exception{
     //条件：查询name=小明并且age>=10的
         List<User> users= userDAO.findByNameAndAgeGreaterThanEqual("小明",20);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike () throws Exception{
     //模糊查询(Like)
       List<User> users=userDAO.findByNameLike("%小%");
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryByName () throws Exception{
        //使用query注解查询
     List<User> users= userDAO.queryUserByNameUseJPQL("小明");
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryLike () throws Exception{
        //使用query注解模糊查询
     List<User> users= userDAO.queryByLike("%小%");
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser () throws Exception{
       userDAO.updateUser("南宁",1);
    }

    @Test
    public void testPage () throws Exception{
     //分页查询
        int page=0;//当前页，索引
        int size=3;//每页显示条数
        Pageable pageable=new PageRequest(page,size);
        Page<User> users= userDAO.findAll(pageable);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSortDemo01 () throws Exception{
        //（测试失败）
     //对象指定的列做排序
     //Direction排序的规则
     //properties指定做排序的属性(对应类中的字段，而不是表中的)
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        List<User> users= userDAO.findAll(sort);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSortDemo02 () throws Exception{
        //对象多列排序做排序
        //Direction排序的规则
        //properties指定做排序的属性
        Sort.Order order1=new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order2=new Sort.Order(Sort.Direction.ASC,"age");
        Sort sort=new Sort(order1,order2);
        List<User> users= userDAO.findAll(sort);
        for (User user :users) {
            System.out.println(user);
        }
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
