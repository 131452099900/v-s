package org.example.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.until.ConstValue;
import org.example.until.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登陆")
@RestController // 注解类为控制器类
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login/info")
    public ResponseBean getLoginUserInfo(@RequestBody String token) {
        try {
            //String token = request.getHeader("Authorization"); // 从请求头中取出token
            System.out.println("getLoginUserInfo读取token: "+token);
            JWT jwt = JWTUtil.parseToken(token); // 转换token为JWT对象
            String idCard = jwt.getPayload("idCard").toString(); // 获取token中携带的idCard值
            System.out.println("getLoginUserInfo返回数据: "+idCard);
            QueryWrapper<User> query = new QueryWrapper<>();
            query.eq("id_card",idCard); // 设置查询的条件
            return ResponseBean.success(userService.getOne(query)); // 注意：getOne()要求表中只有一个值符合查询结果！
        } catch (Exception exp) {
            return ResponseBean.failure();
        }
    }
    @PostMapping("/login") // 映射url="/login"
    @ResponseBody
    public ResponseBean login(@RequestBody User loginUser) { // 注解作用是按json格式接收数据
        System.out.println("login: "+loginUser);
        // 验证登陆用户是否注册：判断数据库中是否存在此用户信息
        User regUser = userService.login(loginUser); // 调用登陆业务
        System.out.println(regUser);
        if (regUser != null) { // 用户不为空，是合法的注册用户
            // 生成JWT的token
            String token = this.createToken(regUser.getIdCard(),regUser.getPassword());
            return ResponseBean.success(token); // 响应登陆
        } else { // 不是注册用户
            return ResponseBean.failure();
        }
    }
    //原文链接：https://blog.csdn.net/qq125281823/article/details/120960181
    private String createToken(String idCard,String password) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, ConstValue.JWT_VALID_TIME);

        Map<String,Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 有效载荷
        payload.put("idCard", idCard);
        payload.put("password", password);

        String key = ConstValue.JWT_APP_KEY;
        String token = JWTUtil.createToken(payload, key.getBytes());
        //System.out.println("生成jwt token: "+token);
        return token;
    }
}