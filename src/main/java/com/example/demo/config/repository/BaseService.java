package com.example.demo.config.repository;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex
 * @date 5/10/2021 4:19 PM
 */
@Transactional
public interface BaseService<T, ID extends Serializable> {

    T save(T t);

    void delete(T t);

    void delete(ID id);

    void update(T t);

    List<T> findAll();

    T get(ID id);

    List<T> find(T t, PageBean pageBean);

    List<T> find(T t, PageBean pageBean, String[] orderBy);

    int count(T t);

    TableData<T> findTableData(T t, PageBean pageBean);

    TableData<T> findTableData(T t, String[] orderBy, PageBean pageBean);

    TableData<T> findTableData(T t, LikeField[] likeFields, PageBean pageBean);

    TableData<T> findTableData(T t, LikeField[] likeFields, String[] orderBy, PageBean pageBean);

    TableData<T> findTableData(T t, String[] likeFields, String[] orderBy, PageBean pageBean);

    void batchInsert(List<T> list);

}
