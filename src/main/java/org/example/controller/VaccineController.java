package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.pojo.Vaccine;
import org.example.service.VaccineService;
import org.example.until.ResponseBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "疫苗管理")
@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    @Resource
    private VaccineService vaccineService;

    @ApiOperation("查询列表") // 显示所有种类的疫苗
    @GetMapping("/list")
    public ResponseBean selectAll(){
        List<Vaccine> list = vaccineService.list();
        System.out.println(list);
        return ResponseBean.success(list);
    }

    @ApiOperation("分页查询列表")
    @GetMapping("/page")
    @ResponseBody
    public ResponseBean list(@RequestParam int pageNo, @RequestParam int size){
        System.out.println("list:"+pageNo);
        Page<Vaccine> page=new Page<>(pageNo,size);
//        page.setCurrent(PageNo);
//        page.setSize(size);
        Page<Vaccine> pages= vaccineService.page(page,null);
        System.out.println("pages"+pages.getPages());
        System.out.println("total"+pages.getTotal());
        System.out.println(pages.getRecords());
        return ResponseBean.success(pages);
    }
    @ApiOperation("添加疫苗")
    @PostMapping
    public ResponseBean addVacType(@RequestBody Vaccine vaccine){
        System.out.println("添加疫苗："+vaccine);
        boolean result=vaccineService.save(vaccine);
        if (result){
            return ResponseBean.success(result);
        }else {
            return ResponseBean.failure(result);
        }
    }

    @ApiOperation("删除疫苗")
    @DeleteMapping("/{id}")
    public ResponseBean delVacType(@PathVariable int id){
        System.out.println("删除疫苗种类的编号："+id);
        boolean result=vaccineService.removeById(id);
        if (result){
            return ResponseBean.success(result);
        }else {
            return ResponseBean.failure(result);
        }
    }

    @ApiOperation("修改疫苗")
    @PutMapping
    public ResponseBean updateVacType(@RequestBody Vaccine vaccine){
        System.out.println("修改疫苗种类："+vaccine);
        boolean result=vaccineService.updateById(vaccine);
        if (result){
            return ResponseBean.success(result);
        }else {
            return ResponseBean.failure(result);
        }
    }
    // 查询一个疫苗(根据疫苗编号查询)
    @ApiOperation("查询疫苗")
    @GetMapping("/{id}")
    public ResponseBean selectByName(@PathVariable int id){
        System.out.println("selectByName："+id);
        System.out.println(vaccineService);
        Vaccine vaccine = vaccineService.getById(id);
        return ResponseBean.success(vaccine);
    }
    // 疫苗分类查询
    @ApiOperation("分类查询疫苗")
    @GetMapping("/type")  //映射的url = "/vaccine/{type}"
    public ResponseBean typeList(@RequestParam int pageNo, @RequestParam int size, @RequestParam int vacTypeId){
        System.out.println("查询类型："+vacTypeId);
        Page<Vaccine> page=new Page<>();
        page.setCurrent(pageNo);
        page.setSize(size);
        QueryWrapper<Vaccine> query = new QueryWrapper<>();
        //设置条件
        query.eq("vac_type_id", vacTypeId);
        Page<Vaccine> pages=vaccineService.page(page,query);
        return ResponseBean.success(pages);
    }
}
