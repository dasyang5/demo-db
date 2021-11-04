package com.example.demo.service;

import com.example.demo.config.repository.BaseService;
import com.example.demo.entity.GoodsType;
import com.example.demo.repository.GoodsTypeRepository;

public interface GoodsTypeService extends BaseService<GoodsType,String> {

    GoodsType getGoodsTypeByType(String type);
}
