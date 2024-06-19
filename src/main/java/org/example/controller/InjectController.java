package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.pojo.Inject;
import org.example.service.InjectService;
import org.example.until.ResponseBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "预约接种管理")
@RestController
@RequestMapping("/order")
public class InjectController {
    @Resource
    private InjectService injectService;

    @ApiOperation("预约")
    @PostMapping
    public ResponseBean createOrder(@RequestBody JSONObject json) {
        System.out.println("create order: "+json);
        int userId = json.getObject("userId",Integer.class);
        int inoSiteId = json.getObject("inoSiteId",Integer.class);
        String times = json.getObject("times",String.class);
        System.out.println(userId+", "+inoSiteId+", "+times);
        boolean result = injectService.createOrder(userId,inoSiteId,times);
        if (result) {
            return ResponseBean.success(true);
        } else {
            return ResponseBean.failure(false);
        }
    }

//    @ApiOperation("预约分页列表")
//    @GetMapping
//    public ResponseBean orderList(@RequestParam int pageNo,@RequestParam int size,@RequestParam int userId){
//        // 新建分页对象
//        Page<Inject> page = new Page<>(pageNo,size);
//        return ResponseBean.success(injectService.list(page,userId));
//    }

    @ApiOperation("查询列表")
    @GetMapping("/list")
    public ResponseBean selectAll() {
        List<Inject> list = injectService.list();
        System.out.println(list);
        return ResponseBean.success(list);
    }

    @DeleteMapping("/{id}")
    public ResponseBean delete(@PathVariable int id){
        boolean result = injectService.removeById(id);
        if (result){
            return ResponseBean.success(result);
        }else{
            return ResponseBean.failure();
        }
    }
}
