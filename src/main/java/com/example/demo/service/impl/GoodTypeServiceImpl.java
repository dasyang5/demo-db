package com.example.demo.service.impl;

import com.example.demo.config.repository.BaseServiceImpl;
import com.example.demo.entity.GoodsType;
import com.example.demo.entity.User;
import com.example.demo.repository.GoodsTypeRepository;
import com.example.demo.service.GoodsTypeService;
import org.springframework.stereotype.Service;

@Service
public  class GoodTypeServiceImpl extends BaseServiceImpl<GoodsType, String> implements GoodsTypeService {

    private GoodsTypeRepository goodsTypeRepository;

    @Override
    public GoodsType getGoodsTypeByType(String type) {
        return goodsTypeRepository.findGoodsTypeByType(type);
    }
}
