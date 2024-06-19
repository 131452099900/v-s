package org.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.pojo.VacType;
import org.example.service.VacTypeService;
import org.example.until.ResponseBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "疫苗类型管理")
@RestController
@RequestMapping("/vactype")
public class VacTypeController {
    @Resource
    private VacTypeService vacTypeService;

    @ApiOperation("查询列表") //显示所有种类的疫苗
    @GetMapping("/list")
    public ResponseBean selectAll(){
        List<VacType> list = vacTypeService.list();
        System.out.println(list);
        return ResponseBean.success(list);
    }

    @ApiOperation("分页查询列表")
    @GetMapping("/page")
    public ResponseBean list(@RequestParam int pageNo,@RequestParam int size){
        System.out.println("list: "+pageNo);
        Page<VacType> page=new Page<>(pageNo,size);
//        page.setCurrent(PageNo);
//        page.setSize(size);
        Page<VacType> pages= vacTypeService.page(page,null);
        System.out.println("pages: "+pages.getPages());
        System.out.println("total: "+pages.getTotal());
        System.out.println(pages.getRecords());
        return ResponseBean.success(pages);
    }

    @ApiOperation("添加疫苗种类")
    @PostMapping
    public ResponseBean addVacType(@RequestBody VacType vacType){
        System.out.println("添加疫苗种类："+vacType);
        boolean result=vacTypeService.save(vacType);
        if (result){
            return ResponseBean.success(true);
        }else {
            return ResponseBean.failure(false);
        }
    }

    @ApiOperation("删除疫苗种类")
    @DeleteMapping("/{id}")
    public ResponseBean delVacType(@PathVariable int id){
        System.out.println("删除疫苗种类的编号："+id);
        boolean result=vacTypeService.removeById(id);
        if (result){
            return ResponseBean.success(true);
        }else {
            return ResponseBean.failure(false);
        }
    }

    @ApiOperation("修改疫苗种类")
    @PutMapping
    public ResponseBean updateVacType(@RequestBody VacType vacType){
        System.out.println("修改疫苗种类："+vacType);
        boolean result=vacTypeService.updateById(vacType);
        if (result){
            return ResponseBean.success(true);
        }else {
            return ResponseBean.failure(false);
        }
    }
}
