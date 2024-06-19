package org.example.controller;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.until.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 功能：实现用户管理业务相关请求的处理
 */
@Api(tags = "用户管理") // 注解作用：把UserController类的功能或作用告诉swagger
@Controller // 注解作用：注解类成为一个控制器类，可以接受、响应web请求
@RequestMapping("/user") // 把url路径中包含有"/user"的请求交给注解类UserController来处理
public class UserController {
    @Resource //从spring容器获取接口实现类的对象
    private UserService userService;

//    @PostMapping("/getinfo")  // 处理url="/user/getinfo"请求，交给注解的方法index()来处理
//    public ResponseBean index(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        System.out.println("处理用户信息获取: "+token);
//        if (token != null) {
//            // 验证请求，这里使用Hutool工具作jwt验证
//            JWT jwt = JWTUtil.parseToken(token); // 转换为JWT对象
//            String key = ConstValue.JWT_APP_KEY;
//            String idCard = jwt.getPayload("id_card").toString();
//            QueryWrapper<User> query = new QueryWrapper<>();
//            query.eq("id_card",idCard);
//            return ResponseBean.success(userService.getOne(query));
//        } else return ResponseBean.failure();
//    }
    /**
     * getUser
     * 功能：根据所传参数获取一个用户对象
     * @param id 用户id
     * @return 用户对象
     * 注：Restful风格API
     */
    @ApiOperation("获取一个用户") // 注解先放Swagger此API的用途
    @GetMapping("/{id}") // 映射url="/user/{id}"
    @ResponseBody // 把返回的数据转换成json格式，响应给浏览器
    public ResponseBean getUser(@PathVariable int id) { // 获取url路径上传递的参数id
        System.out.println("getUser()接受到id: " + id);
        // 请求业务，获取用户对象数据
        User user=userService.findById(id);
        return ResponseBean.success(user); // 生成用户对象
    }

    /**
     * userList
     * 功能：请求多个用户，获取用户分页列表
     * @param page 页码
     * @return 分页列表
     */
    @ApiOperation("获取用户分页列表")
    @GetMapping // 映射url="/user?page={page}"
    @ResponseBody // 把返回的数据转换成json格式，响应给浏览器
    public ResponseBean userList(@RequestParam int page) { // RequestParam注解的作用：接收请求参数
        // 请求列表业务
        Map<String,Object> map = userService.list(page,10);
        return ResponseBean.success(map); // 返回响应数据，以json格式呈现数据
    }

    /**
     * searchPage
     * 功能：搜索分页
     * @param json json格式的数据（所查询的用户信息）
     * @return 符合查询条件的列表数据
     */
    @ApiOperation("搜索分页")
    @PostMapping("/page") // 映射url="/user/page"
    @ResponseBody
    // 当参数中包含多个对象，比较复杂时，使用@RequestBody，接受json格式的数据
    public ResponseBean searchPage(@RequestBody JSONObject json) {
        //System.out.println(json);
        // 把json数据转换成Java对象
        User user = json.getObject("user",User.class);
        Page<User> page = json.getObject("page",Page.class);
        //System.out.println(user);
        //System.out.println(page);
        // 调用业务，实现分页
        QueryWrapper<User> query = new QueryWrapper<>();
        // 查询用户表中，列名为id_card的列所对应值的记录
        query.eq("id_card",user.getIdCard());
        Page<User> result = userService.page(page,query);
        result.setPages(1);
        return ResponseBean.success(result);
    }
    // 添加用户
    @ApiOperation("添加用户")
    @PostMapping // 映射url="/user"
    @ResponseBody // 接受json格式数据时，这个注解必须有
    public ResponseBean addUser(@RequestBody User user) { // 接受对象参数user
        System.out.println("addUser:" + user); // 检查http请求传递的参数
        // 请求添加业务
        boolean result = userService.save(user);
        if (result) {
            return ResponseBean.success(true);
        } else {
            return ResponseBean.failure(false);
        }
    }
    // 更新用户信息
    @ApiOperation("更新用户信息")
    @PutMapping // 映射url="/user"
    @ResponseBody
    public ResponseBean updateUser(@RequestBody User user) { // 接受单独参数
        System.out.println("update: "+user);
        // 请求更新业务，传递user对象
        boolean result = userService.update(user);
        if (result) {
            return ResponseBean.success(true);
        } else {
            return ResponseBean.failure(false);
        }
    }
    // 删除用户
    @ApiOperation("删除用户")
    @DeleteMapping("/{id}") // 映射url="/user/{id}"
    @ResponseBody
    public ResponseBean deleteUser(@PathVariable int id) {
        System.out.println("deleteUser:"+id);
        // 请求删除业务，传递数据id
        boolean result = userService.delete(id);
        if (result) {
            return ResponseBean.success(true);
        }
        return ResponseBean.failure(false);
    }
}
