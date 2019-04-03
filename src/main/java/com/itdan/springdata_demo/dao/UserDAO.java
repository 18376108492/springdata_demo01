package com.itdan.springdata_demo.dao;

import com.itdan.springdata_demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
//JpaRepository<User,Integer> 第一个参数：我们要操作的类，第二个参数：该类的主键是什么类型的
//JpaSpecificationExecutor该接口不能单独使用，要配合JpaRepository
public interface UserDAO extends Repository<User,Integer>,
        JpaRepository<User,Integer> ,
        JpaSpecificationExecutor<User> {

    /**
     * 根据user_name 查询出user对象
     * @param name
     * @return
     */
    List<User> findByNameIs(String name);

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    List<User> findByNameLike(String keyword);

    /**
     * 条件查询
     * @param name
     * @param age
     * @return
     */
    List<User> findByNameAndAgeGreaterThanEqual(String name,Integer age);


    //使用Query标签查询
    //nativeQuery:开启sql查询
    @Query(value=" select * from user where u_name= ?",nativeQuery = true)
    List<User> queryUserByNameUseJPQL(String name);

    //模糊查询
    @Query(value=" select * from user where u_name like ?",nativeQuery = true)
    List<User> queryByLike(String keyword);

    //更新语句
    /*
    * 异常： Executing an update/delete query
    解决方案
    因为jpa要求，’没有事务支持，不能执行更新和删除操作’。
    所以反过来讲，就是在Service层或者Repository层上必须加@Transactional，
    来代表这是一个事务级别的操作，增删改查除了查都是事务级别的，就当这是一个规范也是ok的。
    * */
    @Query(value = "UPDATE user set u_add=? where u_id=?",nativeQuery = true)
    @Modifying
    void updateUser(String add,Integer id);
}
