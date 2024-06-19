package org.example.interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.example.until.ConstValue;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器，检查来自前端的请求头中 有无token
 */
public class JwtInterceptor implements HandlerInterceptor {
    // 在请求交给控制器之前，作拦截处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("拦截请求，检查token");
//        // 取出token
//        String token = request.getHeader("Authorization");
//        System.out.println("拦截器，读取token："+token);
//        if (token != null) {
//            // 验证请求，这里使用Hutool工具作jwt验证
//            // 原文链接：https://blog.csdn.net/qq125281823/article/details/120960181
//            JWT jwt = JWTUtil.parseToken(token); // 转换为JWT对象
//            String key = ConstValue.JWT_APP_KEY;
//            boolean verifyKey = jwt.setKey(key.getBytes()).verify(); // 校验JWT对象
//            System.out.println("jwt verifyKey: "+verifyKey);
//
//            boolean verifyTime = jwt.validate(0); // 验证token是否过期
//            System.out.println("jwt validate time: "+verifyTime);
//            if (verifyKey && verifyTime) { // token检验合法
//                return true; // true：放行请求，false：拦截请求
//            } else {
//                throw new RuntimeException("token验证失败!");
//            }
//        } else { // token为空
//            throw new RuntimeException("token为空");
//        }
        return true;
    }

    // 在控制器响应返回后，作拦截处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView)
            throws Exception {
        System.out.println("拦截请求，后置处理!");
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}