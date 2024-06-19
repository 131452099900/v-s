package org.example.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置mybatis-plus分页拦截器
@Configuration // 注解类为配置类
@MapperScan("org.example.mapper") // 扫描Mapper接口包路径
public class MyBatisConfig {
    /**
     * 解决：Page返回对象page=0，total=0的问题
     * mybatis分页，新版本的方法就是 MybatisPlusInterceptor
     */
    @Bean // 把方法返回值对象mybatisPlusInterceptor注入spring容器
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
