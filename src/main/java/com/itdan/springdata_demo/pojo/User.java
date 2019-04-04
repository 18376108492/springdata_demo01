package com.itdan.springdata_demo.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
    @Column(name = "u_id")
    private int id;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_age")
    private int age;
    @Column(name = "u_add")
    private String add;

    @JoinColumn(name = "roles_id")
    //一对一操作@OneToOne(cascade = CascadeType.PERSIST)//级联
    @ManyToOne(cascade = CascadeType.PERSIST)//一对多操作
    private Roles roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", add='" + add + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
