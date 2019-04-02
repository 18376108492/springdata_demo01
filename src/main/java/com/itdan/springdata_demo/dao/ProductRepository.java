package com.itdan.springdata_demo.dao;

import com.itdan.springdata_demo.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 产品管理
 */

public   interface ProductRepository extends
        CrudRepository<Product,String>,
        JpaRepository<Product, String>,
        JpaSpecificationExecutor<Product> {
}