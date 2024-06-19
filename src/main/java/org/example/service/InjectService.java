package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.Inject;

// 预约接种管理服务接口
public interface InjectService extends IService<Inject> {
    // 预约
    boolean createOrder(int userId, int inoSiteId, String times);
    //Page<Inject> list(Page<Inject> page, int userId);
}