package com.itdan.springdata_demo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private Integer id;

    @Column(name = "roles_name")
    private String name;

    //@OneToOne(mappedBy = "roles")
    //private User user;

    //@OneToMany(mappedBy = "roles")
    //private Set<User> users=new HashSet<>();

    @ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(name="t_roles_menus",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="menu_id")) //用来配置中间表的
    private Set<Menus> menus=new HashSet<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Set<Menus> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menus> menus) {
        this.menus = menus;
    }
}
