package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper接口
 * 用户数据访问映射器接口，继承使用mybatis-plus提供的基本CRUD操作方法
 */
@Repository // 注解，向spring容器注入对象userMapper
public interface UserMapper extends BaseMapper<User> {
    int delete(int id); // 根据id删除数据
    /**
     * 分页查询
     * @param start 起始记录行号
     * @param size 每页显示的记录数
     * @return 用户列表
     */
    List<User> list(@Param("start") int start, @Param("size") int size);
    int count(); // 统计方法
    // 对于h_user表中id大于所传参数deletedId的数据项，id减一
    Integer decrementAfterId(Integer deletedId);
}
