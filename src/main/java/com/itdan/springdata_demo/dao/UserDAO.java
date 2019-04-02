package com.itdan.springdata_demo.dao;

import com.itdan.springdata_demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//JpaRepository<User,Integer> 第一个参数：我们要操作的类，第二个参数：该类的主键是什么类型的
public interface UserDAO extends JpaRepository<User,Integer> ,JpaSpecificationExecutor<User> {
}
