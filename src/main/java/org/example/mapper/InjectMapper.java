package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.Inject;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectMapper extends BaseMapper<Inject> {
    // 生成接种预约
    int createOrder(@Param("userId") int userId, @Param("inoSiteId") int inoSiteId,
                    @Param("times") String times);
}
