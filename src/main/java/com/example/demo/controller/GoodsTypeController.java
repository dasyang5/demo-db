package com.example.demo.controller;


import com.example.demo.bean.BaseController;
import com.example.demo.bean.RestResponse;
import com.example.demo.entity.GoodsType;
import com.example.demo.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("goodsType")
public class GoodsTypeController extends BaseController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @PostMapping("add")
    public RestResponse add(GoodsType goodsType) {

        goodsType.setCreTime(new Date());
        goodsType.setCreTime(new Date());

        goodsTypeService.save(goodsType);
        return success();
    }

    @GetMapping("get")
    public GoodsType get(String type) {
        return goodsTypeService.getGoodsTypeByType(type);
    }

    @GetMapping("list")
    public RestResponse list(GoodsType goodsType) {
        return success().tableData(goodsTypeService.findTableData(goodsType, getPageBean()));
    }

    /**
     * 按照ID删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public RestResponse delete(@PathVariable(name = "id") String id) {
        goodsTypeService.delete(id);
        return success();
    }

    /**
     * 按照type删除
     * @param type
     * @return
     */
    @DeleteMapping("/deleteByType")
    public RestResponse deleteByType(String type) {

        GoodsType goodsType = goodsTypeService.get(type);

        if (goodsType != null) {
            goodsTypeService.delete(goodsType);
        }

        return success();
    }


}
