package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.InoSite;

import java.util.List;

// 接种点管理服务接口
public interface InoSiteService extends IService<InoSite> {
    InoSite findById(int id); // 查询接种点
    List<InoSite> list(); // 列表数据
    boolean save(InoSite inoSite); // 添加
    boolean update(InoSite inoSite); // 更新
    boolean delete(int id); // 删除
}
