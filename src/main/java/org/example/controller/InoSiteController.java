package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.pojo.InoSite;
import org.example.service.InoSiteService;
import org.example.until.ResponseBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "接种点管理")
@RestController
@RequestMapping("/inosite")
public class InoSiteController {
    @Resource //从spring容器获取接口实现类的对象
    private InoSiteService inoSiteService;

    @ApiOperation("查询接种点信息")
    @GetMapping("/{id}") // url="inosite/{id}"
    @ResponseBody
    public ResponseBean getInoSite(@PathVariable int id) {
        System.out.println("接种点id: "+id);
        // 请求业务，获取对象数据
        InoSite inoSite = inoSiteService.findById(id);
        return ResponseBean.success(inoSite); // 生成对象
    }
    @ApiOperation("搜索分页")
    @PostMapping("/page")  //url="/inosite/page"
    @ResponseBody
    public ResponseBean searchPage(@RequestBody JSONObject json){ //接收了json格式的数据，参数中包含了多个对象数
        System.out.println(json);
        //把json数据转换成Java对象
        InoSite inoSite =json.getObject("inoSite",InoSite.class);
        Page<InoSite> page =json.getObject("page",Page.class);
        System.out.println(inoSite);
        System.out.println(page);
        //调用业务,实现分页
        QueryWrapper<InoSite> query = new QueryWrapper<>();
        query.eq("name",inoSite.getName());
        Page<InoSite> result = inoSiteService.page(page,query);
        //result.setPages(1);
        return ResponseBean.success(result);
    }
    @ApiOperation("接种点列表")
    @GetMapping("/list") // url="inosite/list"
    public ResponseBean list() {
        return ResponseBean.success(inoSiteService.list());
    }
    // 分页列表
    @ApiOperation("分页列表")
    @GetMapping("/page") // url="/inosite/page"
    public ResponseBean pageList(@RequestParam int pageNo,@RequestParam int size) {
        System.out.println("列表: "+pageNo);
        Page<InoSite> page = new Page<>(pageNo,size);
        Page<InoSite> result = inoSiteService.page(page,null);
        return ResponseBean.success(result);
    }
    // 添加操作
    @ApiOperation("添加操作")
    @PostMapping // url="/inosite"
    @ResponseBody
    public ResponseBean addInoSite(@RequestBody InoSite inoSite) {
        System.out.println("添加: "+inoSite);
        boolean result = inoSiteService.save(inoSite);
        if (result) {
            return ResponseBean.success(true);
        } else {
            return ResponseBean.failure(false);
        }
    }
    // 更新操作
    @ApiOperation("更新操作")
    @PutMapping // url="/inosite"
    @ResponseBody
    public ResponseBean updateInoSite(@RequestBody InoSite inoSite) {
        System.out.println("更新: "+inoSite);
        boolean result = inoSiteService.update(inoSite);
        if (result) {
            return ResponseBean.success(true);
        } else {
            return ResponseBean.failure(false);
        }
    }
    // 删除操作
    @ApiOperation("删除操作")
    @DeleteMapping("/{id}") // url="/inosite/{id}"
    @ResponseBody
    public ResponseBean deleteInoSite(@PathVariable int id) {
        boolean result = inoSiteService.delete(id);
        if (result) {
            return ResponseBean.success(true);
        } else {
            return ResponseBean.failure(false);
        }
    }
}
