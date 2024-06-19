package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.InoSiteMapper;
import org.example.pojo.InoSite;
import org.example.service.InoSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class InoSiteServiceImpl extends ServiceImpl<InoSiteMapper, InoSite>
        implements InoSiteService {
    @Resource
    private InoSiteMapper inoSiteMapper;

    @Override
    public InoSite findById(int id) {
        return inoSiteMapper.selectById(id);
    }
    // 列表数据
    @Override
    public List<InoSite> list() {
        QueryWrapper<InoSite> query = new QueryWrapper<>();
        return inoSiteMapper.selectList(query);
    }
    // 添加
    @Override
    public boolean save(InoSite inoSite) {
        Integer id_temp = inoSiteMapper.count()+1; // 实现新添加的数据id自增：将其赋值为表中数据总数加一
        inoSite.setId(id_temp);
        int result = inoSiteMapper.insert(inoSite);
        return result>0;
    }
    // 更新
    @Override
    public boolean update(InoSite inoSite) {
        return inoSiteMapper.updateById(inoSite)>0;
    }
    // 删除
    @Override
    public boolean delete(int id) {
        return inoSiteMapper.deleteById(id)>0;
    }
}
