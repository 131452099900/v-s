package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.VaccineMapper;
import org.example.pojo.Vaccine;
import org.example.service.VaccineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class VaccineServiceImpl extends ServiceImpl<VaccineMapper, Vaccine>
        implements VaccineService {
    @Resource
    private VaccineMapper vaccineMapper;

    @Override
    public Vaccine findById(int id) {
        return vaccineMapper.selectById(id);
    }
    // 列表数据
    @Override
    public List<Vaccine> list() {
        QueryWrapper<Vaccine> query = new QueryWrapper<>();
        return vaccineMapper.selectList(query);
    }
    // 添加
    @Override
    public boolean save(Vaccine vaccine) {
        return vaccineMapper.insert(vaccine)>0;
    }
    // 更新
    @Override
    public boolean update(Vaccine vaccine) {
        return vaccineMapper.updateById(vaccine)>0;
    }
    // 删除
    @Override
    public boolean delete(int id) {
        return vaccineMapper.deleteById(id)>0;
    }
    // 疫苗分类查询列表
    @Override
    public Page<Vaccine> vacTypeList(int pageNo, int size, int vacTypeId) {
        System.out.println("疫苗分类查询列表");
        Page<Vaccine> page=new Page<>(pageNo,size);
        QueryWrapper<Vaccine> query= new QueryWrapper<>();
        query.eq("vac_type_id",vacTypeId);
        return vaccineMapper.selectPage(page,query);
    }
}
