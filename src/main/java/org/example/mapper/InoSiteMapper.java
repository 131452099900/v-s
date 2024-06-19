package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.InoSite;
import org.springframework.stereotype.Repository;

@Repository
public interface InoSiteMapper extends BaseMapper<InoSite> {
    //InoSite selectInoSiteByOrderId(int id);
    int count(); // 统计方法
}
