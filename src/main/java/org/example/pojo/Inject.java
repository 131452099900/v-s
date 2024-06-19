package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * 接种预约类--对应t_inject表
 */
@TableName(value = "t_inject",resultMap = "injectMap")
public class Inject {
    @TableId // 注解主键
    private Integer id; // id INT PRIMARY KEY auto_increment, 编号
    private Integer userId; // user_id INT, 用户id
    private Integer inoSiteId; // ino_site_id INT, 接种点id
    private Date date; // date DATE, 接种日期
    private String times; // times VARCHAR(20), 接种时间: “上午”或“下午”
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String inoSiteName;
    @TableField(exist = false)
    private User userBean; // 一对一关联对象
    private InoSite inoSiteBean;

    @Override
    public String toString() {
        return "Inject{" +
                "id=" + id +
                ", userId=" + userId +
                ", inoSiteId=" + inoSiteId +
                ", date=" + date +
                ", times='" + times + '\'' +
                ", userName='" + userName + '\'' +
                ", inoSiteName='" + inoSiteName + '\'' +
                ", userBean=" + userBean +
                ", inoSiteBean=" + inoSiteBean +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInoSiteId() {
        return inoSiteId;
    }

    public void setInoSiteId(Integer inoSiteId) {
        this.inoSiteId = inoSiteId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public InoSite getInoSiteBean() {
        return inoSiteBean;
    }

    public void setInoSiteBean(InoSite inoSiteBean) {
        this.inoSiteBean = inoSiteBean;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInoSiteName() {
        return inoSiteName;
    }

    public void setInoSiteName(String inoSiteName) {
        this.inoSiteName = inoSiteName;
    }
}