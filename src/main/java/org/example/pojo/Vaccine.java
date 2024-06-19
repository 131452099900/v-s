package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 疫苗类--对应t_vaccine表
 */
@TableName(value = "t_vaccine",resultMap = "vaccineMap")
public class Vaccine {
    @TableId
    private Integer id; // id INT PRIMARY KEY auto_increment, 编号
    private String name; //name VARCHAR(64), 名称
    private String company; // company VARCHAR(64), 生产厂商
    private Integer vacTypeId; // vac_type_id INT 疫苗类型编号(一对一级联所用字段)
    @TableField(exist = false)
    private VacType vacTypeBean; // 一对一关联对象
    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", vacTypeId=" + vacTypeId +
                ", vacTypeBean=" + vacTypeBean +
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getVacTypeId() {
        return vacTypeId;
    }

    public void setVacTypeId(Integer vacTypeId) {
        this.vacTypeId = vacTypeId;
    }

    public VacType getVacTypeBean() {
        return vacTypeBean;
    }

    public void setVacTypeBean(VacType vacTypeBean) {
        this.vacTypeBean = vacTypeBean;
    }
}
