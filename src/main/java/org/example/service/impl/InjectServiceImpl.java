package org.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.InjectMapper;
import org.example.pojo.Inject;
import org.example.service.InjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class InjectServiceImpl extends ServiceImpl<InjectMapper,Inject>
        implements InjectService {
    @Resource
    private InjectMapper injectMapper;

    @Override
    public boolean createOrder(int userId, int inoSiteId, String times) {
         LambdaQueryWrapper<Inject> wrapper = new LambdaQueryWrapper<>();
        Inject inject = lambdaQuery().eq(Inject::getUserId, userId).eq(Inject::getInoSiteId, inoSiteId).one();
        if (ObjectUtil.isNotNull(inject)) {
            return false;
        }
        return injectMapper.createOrder(userId,inoSiteId,times)>0;
    }
}
