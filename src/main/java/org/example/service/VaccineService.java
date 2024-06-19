package org.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Vaccine;

import java.util.List;

// 疫苗管理服务接口
public interface VaccineService extends IService<Vaccine> {
    Vaccine findById(int id); // 查询疫苗
    List<Vaccine> list(); // 列表数据
    boolean save(Vaccine vaccine); // 添加
    boolean update(Vaccine vaccine); // 更新
    boolean delete(int id); // 删除
    // 疫苗分类查询列表
    Page<Vaccine> vacTypeList(int pageNo, int size, int vacTypeId);
}
