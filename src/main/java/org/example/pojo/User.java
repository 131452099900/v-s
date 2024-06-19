package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 用户类--对应t_user表
 */
@TableName("t_user") // 表名注解，标识实体类对应的表
public class User implements Serializable { // 实现序列化接口
    @TableId // 注解主键
    private Integer id; // id INT PRIMARY KEY auto_increment, 编号
    private String name; // name VARCHAR(64), 名称
    private String idCard; // id_card VARCHAR(64) UNIQUE, 身份证号(也是登陆账号)
    private String password; // password VARCHAR(64), 密码
    private String phone; // phone VARCHAR(20) -- 电话号码
    public User() { } // 无参构造

    public User(Integer id, String name, String idCard, String password, String phone) {
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
