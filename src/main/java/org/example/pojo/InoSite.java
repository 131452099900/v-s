package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * 接种点类--对应t_ino_site表
 * );
 */
@TableName(value = "t_ino_site",resultMap = "inoSiteMap")
public class InoSite {
    @TableId
    private Integer id; // id INT PRIMARY KEY auto_increment, 编号
    private String name; // name VARCHAR(64), 名称
    private String address; // address VARCHAR(64), 地址
    private Date startDate; // start_date DATE, 开始日期
    private Date endDate; // end_date DATE, 结束日期
    private Integer vacId; // vac_id INT 疫苗编号(一对一级联所用字段)
    @TableField(exist = false)
    private Vaccine vaccineBean; // 一对一关联对象

    @Override
    public String toString() {
        return "InoSite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vacId=" + vacId +
                ", vaccineBean=" + vaccineBean +
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getVacId() {
        return vacId;
    }

    public void setVacId(Integer vacId) {
        this.vacId = vacId;
    }

    public Vaccine getVaccineBean() {
        return vaccineBean;
    }

    public void setVaccineBean(Vaccine vaccineBean) {
        this.vaccineBean = vaccineBean;
    }
}
