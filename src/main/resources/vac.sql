-- 创建数据库
CREATE DATABASE vac_db;
-- 打开数据库
USE vac_db;

-- 创建用户表
CREATE TABLE t_user(
    id INT PRIMARY KEY auto_increment, -- 编号
    name VARCHAR(64), -- 名称
    id_card VARCHAR(64) UNIQUE, -- 身份证号(也是登陆账号)
    password VARCHAR(64), -- 密码
    phone VARCHAR(20) -- 电话号码
);
-- 插入用户数据
INSERT INTO t_user(name,id_card,password,phone)
SELECT 'admin','001','123','13686827459'
UNION
SELECT 'userName','002','123','13989647582';

-- 创建接种点表
CREATE TABLE t_ino_site(
    id INT PRIMARY KEY auto_increment, -- 编号
    name VARCHAR(64), -- 名称
    address VARCHAR(64), -- 地址
    start_date DATE, -- 开始日期
    end_date DATE, -- 结束日期
    vac_id INT -- 疫苗编号(一对一级联所用字段)
);
-- 插入接种点数据
INSERT INTO t_ino_site(name,address,start_date,end_date,vac_id)
SELECT '北京蕙兰医院','北京市朝阳区望京北路18号',20220901,20220925,1
UNION
SELECT '安贞街道社区卫生服务中心','北京市朝阳区安华西区',20220901,20220925,1
UNION
SELECT '奥运村街道北园接种点','北京市朝阳区双营路天居园10号楼',20220901,20220925,2
UNION
SELECT '八里庄街道社区卫生服务中心','北京市朝阳区延静西里11楼',20220901,20220925,3
UNION
SELECT '大屯街道社区卫生服务中心','北京市朝阳区安慧北里逸',20220901,20220925,4;

-- 创建疫苗表
CREATE TABLE t_vaccine(
    id INT PRIMARY KEY auto_increment, -- 编号
    name VARCHAR(64), -- 名称
    company VARCHAR(64), -- 生产厂商
    vac_type_id INT -- 疫苗类型编号(一对一级联所用字段)
);
-- 插入疫苗数据
INSERT INTO t_vaccine(name,company,vac_type_id)
SELECT '北科减毒新冠疫苗','北京科兴生物',1
UNION
SELECT '灭活新冠疫苗','中国医学科学院',2
UNION
SELECT '重组亚单位新冠疫苗','北京科兴生物',3
UNION
SELECT '腺病毒载体新冠疫苗','国药集团',4
UNION
SELECT '辉瑞新冠疫苗','辉瑞公司',5
UNION
SELECT '莫纳德新冠疫苗','莫纳德制药集团',6;

-- 创建疫苗类型表
CREATE TABLE t_vac_type(
    id INT PRIMARY KEY auto_increment, -- 编号
    name VARCHAR(64), -- 名称
    number INT, -- 接种次数
    interval_day INT -- 接种间隔天数
);
-- 插入疫苗类型数据
INSERT INTO t_vac_type(name,number,interval_day)
SELECT '减毒疫苗',2,21
UNION
SELECT '灭活疫苗',2,21
UNION
SELECT '蛋白亚单位疫苗',3,14
UNION
SELECT '病毒载体疫苗',1,0
UNION
SELECT '核酸疫苗',2,28
UNION
SELECT '病毒样颗粒疫苗',2,21;

-- 创建接种预约表
CREATE TABLE t_inject(
    id INT PRIMARY KEY auto_increment, -- 编号
    user_id INT, -- 用户id
    ino_site_id INT, -- 接种点id
    date DATE, -- 接种日期
    times VARCHAR(20) -- 接种时间: “上午”或“下午”
);
-- 插入接种预约数据