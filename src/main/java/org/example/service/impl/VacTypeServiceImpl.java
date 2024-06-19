package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.VacTypeMapper;
import org.example.pojo.VacType;
import org.example.service.VacTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class VacTypeServiceImpl extends ServiceImpl<VacTypeMapper, VacType>
        implements VacTypeService {
    @Resource
    private VacTypeMapper vacTypeMapper;

    @Override
    public VacType findById(int id) {
        return vacTypeMapper.selectById(id);
    }
    // 列表数据
    @Override
    public List<VacType> list() {
        QueryWrapper<VacType> query = new QueryWrapper<>();
        return vacTypeMapper.selectList(query);
    }
    // 添加
    @Override
    public boolean save(VacType vacType) {
        return vacTypeMapper.insert(vacType)>0;
    }
    // 更新
    @Override
    public boolean update(VacType vacType) {
        return vacTypeMapper.updateById(vacType)>0;
    }
    // 删除
    @Override
    public boolean delete(int id) {
        return vacTypeMapper.deleteById(id)>0;
    }
}
