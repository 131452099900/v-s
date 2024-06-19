package org.example.service.impl;

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
        return injectMapper.createOrder(userId,inoSiteId,times)>0;
    }
}
