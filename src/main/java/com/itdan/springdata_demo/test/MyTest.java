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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public void testQueryDemo01 () throws Exception{
     //JpaSpecificationExecutor接口单条件查询

        Specification<User> specification=new Specification<User>() {
            /**
             * 定义查询条件
             * @param root 跟对象，封装了查询的对象（User）
             * @param criteriaQuery 定义了一个基本的查询对象，基本不是使用
             * @param criteriaBuilder 创建一个查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               Predicate predicate=criteriaBuilder.equal(root.get("name"),"小明");//相当于 where name="小明"
               return  predicate;
            }
        };
          List<User> users= userDAO.findAll(specification);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryDemo02 () throws Exception{
        //JpaSpecificationExecutor接口多条件查询

        Specification<User> specification=new Specification<User>() {
            /**
             * 定义查询条件
             * @param root 跟对象，封装了查询的对象（User）
             * @param criteriaQuery 定义了一个基本的查询对象，基本不是使用
             * @param criteriaBuilder 创建一个查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                //添加查询条件
                predicateList.add(criteriaBuilder.equal(root.get("name"),"小明"));//相当于 where name="小明"
                predicateList.add(criteriaBuilder.equal(root.get("age"),18));//相当于 where name="小明"
                Predicate[]arr=new  Predicate[predicateList.size()];//数组的长度等于列表的长度
                return  criteriaBuilder.and(predicateList.toArray(arr));
            }
        };
        List<User> users= userDAO.findAll(specification);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryDemo03 () throws Exception{
        //JpaSpecificationExecutor接口多条件查询（方式er）

        Specification<User> specification=new Specification<User>() {
            /**
             * 定义查询条件
             * @param root 跟对象，封装了查询的对象（User）
             * @param criteriaQuery 定义了一个基本的查询对象，基本不是使用
             * @param criteriaBuilder 创建一个查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return  criteriaBuilder.or(criteriaBuilder.equal(root.get("name"),"小明"),
                        criteriaBuilder.equal(root.get("age"),18));
            }
        };
        List<User> users= userDAO.findAll(specification);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryDemo05 () throws Exception{
     //根据查询条件查询结果，并且做分页操作
     //例如查询姓王的并且做出分页处理

        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                 return criteriaBuilder.like(root.get("name"),"%小%");
            }
        };
          //分页条件
        int page=0;
        int size=3;
        Pageable pageable=new PageRequest(page,size);
         Page<User> users=  userDAO.findAll(specification,pageable);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryDemo06 () throws Exception{
        //根据查询条件查询结果，并且做排序操作
        //例如查询姓王的并且做出排序处理

        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"),"%小%");
            }
        };
        //排序条件
        Sort sort=new Sort(Sort.Direction.DESC,"age");
        List<User> users=  userDAO.findAll(specification,sort);
        for (User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryDemo07 () throws Exception{
        //根据查询条件查询结果，并且做分页和排序操作
        //例如查询姓王的并且做出排序处理

        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"),"%小%");
            }
        };
        //排序条件
        Sort sort=new Sort(Sort.Direction.DESC,"age");
        //分页条件(将排序条件存入分页条件中)
        Pageable pageable=new PageRequest(0,3,sort);
        Page<User> users=  userDAO.findAll(specification,pageable);
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
        product.setMemo("一个测试");
        productRepository.save(product);
    }

}
