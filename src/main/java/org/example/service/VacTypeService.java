package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.VacType;

import java.util.List;

// 疫苗种类管理服务接口
public interface VacTypeService extends IService<VacType> {
    VacType findById(int id); // 查询疫苗种类
    List<VacType> list(); // 列表数据
    boolean save(VacType vacType); // 添加
    boolean update(VacType vacType); // 更新
    boolean delete(int id); // 删除
}
