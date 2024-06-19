package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 疫苗类型类--对应t_vaccine表
 */
@TableName("t_vac_type")
public class VacType {
    @TableId
    private Integer id;  // id INT PRIMARY KEY auto_increment, 编号
    private String name; // name VARCHAR(64), 名称
    private Integer number; // number INT, 接种次数
    private Integer intervalDay; // interval_day INT接种间隔时间

    @Override
    public String toString() {
        return "VacType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", intervalDay=" + intervalDay +
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getIntervalDay() {
        return intervalDay;
    }

    public void setIntervalDay(Integer intervalDay) {
        this.intervalDay = intervalDay;
    }
}
