package com.example.demo.repository;

import com.example.demo.config.repository.BaseRepository;
import com.example.demo.entity.GoodsType;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsTypeRepository extends BaseRepository<GoodsType, String> {
    GoodsType findGoodsTypeByType(String type);
}
