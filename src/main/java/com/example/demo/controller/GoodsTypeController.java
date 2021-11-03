package com.example.demo.controller;


import com.example.demo.bean.BaseController;
import com.example.demo.bean.RestResponse;
import com.example.demo.config.repository.PageBean;
import com.example.demo.entity.GoodsType;
import com.example.demo.repository.GoodsTypeRepository;
import com.example.demo.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.example.demo.bean.RestResponse.success;

@RestController
@RequestMapping("goodsType")
public class GoodsTypeController extends BaseController {
    @Autowired
    private GoodsTypeRepository goodsTypeRepository;
    @Autowired
    private GoodsTypeService goodsTypeService;

    @PostMapping("add")
    public RestResponse add(String type){
        GoodsType goodType = new GoodsType();
        goodType.setType(type);
        goodType.setCreTime(new Date());
        goodType.setCreTime(new Date());

        goodsTypeRepository.save(goodType);
        return success();
    }

    @GetMapping("get")
    public GoodsType get(String TypeName){

        return goodsTypeRepository.findGoodsTypesByType(TypeName);
    }

    @GetMapping("list")
    public RestResponse list(GoodsType goodsType){
     return success().tableData(goodsTypeService.findTableData(goodsType,getPageBean()));
    }
    @DeleteMapping("delete/{type}")
    public RestResponse delete(@PathVariable(name = "type") String type) {
        goodsTypeService.delete(type);
        return success();
    }


}
