package com.example.demo.config.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    List<T> findByHql(String hql);

    List<T> findByHql(String hql, Map<String, Object> params);

    List<T> findByHql(String hql, PageBean pageBean);

    List<T> findByHql(String hql, Map<String, Object> params, PageBean pageBean);

    long countByHql(String hql);

    long countByHql(String hql, Map<String, Object> params);

    int executeUpdate(String hql, Map<String, Object> params);

    int executeUpdate(String hql);

    List executeHql(String hql);

    List executeHql(String hql, Map<String, Object> params);

    List findBySql(String sql);

    List findBySql(String sql, Map<String, Object> params);

    List<Map<String, Object>> findMapsBySql(String sql);

    List<Map<String, Object>> findMapsBySql(String sql, Map<String, Object> params);

    /**
     *
     * @param sql
     * @param clazz 返回的类的class
     * @return
     */
    List findObjectsBySql(String sql, Class clazz);

    /**
     *
     * @param sql
     * @param params
     * @param clazz 返回的类的class
     * @return
     */
    List findObjectsBySql(String sql, Map<String, Object> params, Class clazz);

    long countByEntity(T t);

    List<T> findByEntity(T t, PageBean pageBean);

    List<T> findByEntity(T t, PageBean pageBean, String[] orderBy);

    long countByEntityWithLike(T t, LikeField[] likeFields);

    List<T> findByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean);

    List<T> findByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean, String[] orderBy);

    TableData<T> findTableDataByEntity(T t, PageBean pageBean);

    TableData<T> findTableDataByEntity(T t, String[] orderBy, PageBean pageBean);

    TableData<T> findTableDataByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean);

    TableData<T> findTableDataByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean, String[] orderBy);

    void batchInsert(List<T> list);

}
