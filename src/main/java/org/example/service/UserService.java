package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.User;

import java.util.List;
import java.util.Map;

// 用户管理服务接口
public interface UserService extends IService<User> {
    User findById(int id); // 查找用户
    List<User> list(); // 列表用户数据
    Map<String,Object> list(int page, int size); // 分页列表用户数据
    boolean save(User user); // 添加用户
    boolean update(User user); // 更新用户
    boolean delete(int id); // 删除用户
    User login(User loginUser); // 登陆业务
}
