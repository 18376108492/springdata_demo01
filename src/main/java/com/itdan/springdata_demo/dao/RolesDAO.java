package com.itdan.springdata_demo.dao;


import com.itdan.springdata_demo.pojo.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RolesDAO extends Repository<Roles,Integer>,
        JpaRepository<Roles,Integer>,
        JpaSpecificationExecutor<Roles> {
}
