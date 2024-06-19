package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserService接口的实现类
 */
@Transactional // 在业务实现类上开启事务
@Service // 注解作用：向spring容器注入对象userServiceImpl
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource // 从容器获取对象
    private UserMapper userMapper;

    // 通过id查找一个用户
    @Override
    public User findById(int id) {
        // 调用Mapper层方法，获取数据
        return userMapper.selectById(id);
    }
    // 列表用户数据业务
    @Override
    public List<User> list() {
        // 调用Mapper层方法
        Wrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }
    /**
     * 分页列表用户数据
     * @param page 页码
     * @param size 页面内显示的记录条数
     * @return 返回Map中，list为列表数据，pageTotal为总页码数
     */
    @Override
    public Map<String,Object> list(int page, int size) {
        int start = (page-1)*size;
        //  调用Mapper层方法, 查询当前页数据
        List<User> list = userMapper.list(start,size);
        int total = userMapper.count(); // 统计总记录数
        int pageTotal = (total % size == 0)?(total/size):(total/size+1); // 计算总页码数
        Map<String,Object> map = new HashMap<>();
        // 向map中添加返回的键值对数据
        map.put("list",list);
        map.put("pager",pageTotal);
        map.put("total",total);
        return map;
    }
    // 添加用户操作
    @Override
    public boolean save(User user) {
        Integer id_temp = userMapper.count()+1; // 实现新添加的数据id自增：将其赋值为表中数据总数加一
        user.setId(id_temp);
        int result = userMapper.insert(user);
        return result>0;
    }
    // 更新用户操作
    @Override
    public boolean update(User user) {
        // 调用mapper方法
        return userMapper.updateById(user)>0;
    }
    // 删除用户操作
    @Override
    public boolean delete(int id) {
        int old_count = userMapper.count(); // 保存数据被删除前的总数！
        int result = userMapper.delete(id);
        //System.out.println("删除用户返回结果值："+result); // 值为1
        if (id < old_count) { // 如果所删除的数据不是表的最后一条数据，则id排在其后的数据的id减一
            Integer decResult = userMapper.decrementAfterId(id);
            //System.out.println("自减返回值："+decResult); // 值为1
        }
        return result>0;
    }
    // 登陆业务
    @Override
    public User login(User loginUser) {
        String idCard = loginUser.getIdCard(); // 获取登陆账号
        // 根据身份证号，查询注册信息
        QueryWrapper<User> query = new QueryWrapper<>(); // 创建查询对象
        query.eq("id_card",idCard); // 查询数据表id_card字段的值==idCard的记录
        return userMapper.selectOne(query);  // 返回查询的记录
    }
}
