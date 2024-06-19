package org.example.config;

import org.example.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器配置类
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private static final String[] blackNames = {"/user/**","/inosite/**","/vaccine/**","/vactype/**"}; // 拦截
    private static final String[] whiteNames = {"/login","/registry","/swagger-ui.html","/login/user","/"}; // 放行

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义的JWT拦截器对象
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns(blackNames) // 黒名单：拦截请求
                .excludePathPatterns(whiteNames); // 白名单：不拦截，放行请求
    }
    // 向spring容器注入JWT拦截器对象
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}

