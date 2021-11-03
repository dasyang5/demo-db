package com.example.demo.config.repository;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("uncheck")
@Log
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    private final EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.em = entityManager;
    }

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public List<T> findByHql(String hql) {
        Query query = em.createQuery(hql);

        setParameters(null, query);

        return resultList(query);
    }

    @Override
    public List<T> findByHql(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);

        setParameters(params, query);

        return resultList(query);
    }

    @Override
    public List<T> findByHql(String hql, PageBean pageBean) {
        Query query = em.createQuery(hql);
        return resultList(query, pageBean);
    }

    @Override
    public List<T> findByHql(String hql, Map<String, Object> params, PageBean pageBean) {
        Query query = em.createQuery(hql);

        setParameters(params, query);

        return resultList(query, pageBean);
    }

    @Override
    public long countByHql(String hql) {
        return countByHql(hql, null);
    }

    @Override
    public long countByHql(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        setParameters(params, query);
        return (long) query.getResultList().get(0);
    }

    @Override
    public int executeUpdate(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        setParameters(params, query);
        return query.executeUpdate();
    }

    @Override
    public int executeUpdate(String hql) {
        return executeUpdate(hql, null);
    }

    @Override
    public List executeHql(String hql) {
        return executeHql(hql, null);
    }

    @Override
    public List executeHql(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        setParameters(params, query);
        return query.getResultList();
    }

    @Override
    public List findBySql(String sql) {
        return findBySql(sql, null);
    }

    @Override
    public List findBySql(String sql, Map<String, Object> params) {

        Query query = em.createNativeQuery(sql);

        setParameters(params, query);
        return query.getResultList();

    }

    @Override
    public List<Map<String, Object>> findMapsBySql(String sql) {
        return findMapsBySql(sql, null);
    }

    @Override
    public List<Map<String, Object>> findMapsBySql(String sql, Map<String, Object> params) {
        Query query = em.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        setParameters(params, query);
        return query.getResultList();
    }

    @Override
    public List findObjectsBySql(String sql, Class clazz) {
        return findObjectsBySql(sql, null, clazz);
    }

    @Override
    public List findObjectsBySql(String sql, Map<String, Object> params, Class clazz) {
        Query query = em.createNativeQuery(sql, clazz);

        setParameters(params, query);
        return query.getResultList();
    }

    @Override
    public long countByEntity(T t) {
        return countByEntityWithLike(t, null);
    }

    @Override
    public List<T> findByEntity(T t, PageBean pageBean) {
        return findByEntityWithLike(t, null, pageBean);
    }

    @Override
    public List<T> findByEntity(T t, PageBean pageBean, String[] orderBy) {
        return findByEntityWithLike(t, null, pageBean, orderBy);
    }

    @Override
    public long countByEntityWithLike(T t, LikeField[] likeFields) {
        if (t == null) {
            return count();
        }

        StringBuilder hql = new StringBuilder("select count(1) from ");

        Query query = generateHql(t, hql, likeFields, null);

        return (long) query.getResultList().get(0);
    }

    @Override
    public List<T> findByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean) {
        return findByEntityWithLike(t, likeFields, pageBean, null);
    }

    @Override
    public List<T> findByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean, String[] orderBy) {
        if (t == null) {
            return findAll();
        }

        StringBuilder hql = new StringBuilder("from ");

        Query query = generateHql(t, hql, likeFields, orderBy);

        return resultList(query, pageBean);
    }

    @Override
    public TableData<T> findTableDataByEntity(T t, PageBean pageBean) {
        return findTableDataByEntityWithLike(t, null, pageBean);
    }

    @Override
    public TableData<T> findTableDataByEntity(T t, String[] orderBy, PageBean pageBean) {
        return findTableDataByEntityWithLike(t, null, pageBean, orderBy);
    }

    @Override
    public TableData<T> findTableDataByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean) {
        return findTableDataByEntityWithLike(t, likeFields, pageBean, null);
    }

    @Override
    public TableData<T> findTableDataByEntityWithLike(T t, LikeField[] likeFields, PageBean pageBean, String[] orderBy) {
        TableData<T> tableData = new TableData<>();
        tableData.setData(findByEntityWithLike(t, likeFields, pageBean, orderBy));
        tableData.setCount(countByEntityWithLike(t, likeFields));

        return tableData;
    }

    @Override
    public void batchInsert(List<T> list) {
        int max = 20000, index = 0;

        for (T t : list) {
            em.persist(t);
            index++;
            if (index > max) {
                em.flush();
                em.clear();
                index = 0;
            }
        }
        if (index > 0) {
            em.flush();
            em.clear();
        }
    }

    private void setParameters(Map<String, Object> params, Query query) {
        if (params == null) {
            return;
        }
        params.forEach(query::setParameter);
    }

    /**
     * @return
     */
    private List<T> resultList(Query query) {
        return resultList(query, null);
    }

    @SuppressWarnings("unchecked")
    private List<T> resultList(Query query, PageBean pageBean) {
        try {
            if (pageBean == null) {
                return query.getResultList();
            } else {
                return query.setFirstResult(pageBean.getStartPos()).setMaxResults(pageBean.getPageSize()).getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SneakyThrows
    private Query generateHql(T t, StringBuilder hql, LikeField[] likeFields, String[] orderBy) {
        Map<String, Object> params = new HashMap<>();

        Class clazz = t.getClass();

        hql.append(clazz.getSimpleName());

        for (Field f : clazz.getDeclaredFields()) {
            if (f.getAnnotation(Column.class) != null) {
                f.setAccessible(true);
                Object value = f.get(t);
                if (null != value && StringUtils.hasLength(value.toString())) {
                    if (params.isEmpty()) {
                        hql.append(" where ");
                    } else {
                        hql.append(" and ");
                    }
                    LikeField likeField = getMatchLikeField(likeFields, f.getName());
                    if (likeField != null) {
                        FuzzyConfig fuzzyConfig = likeField.getFuzzyConfig();
                        hql.append(f.getName()).append(" like ").append(":").append(f.getName());
                        params.put(f.getName(), fuzzyConfig.getPrefix() + f.get(t) + fuzzyConfig.getSuffix());
                    } else {
                        hql.append(f.getName()).append(" = ").append(":").append(f.getName());
                        params.put(f.getName(), f.get(t));
                    }
                }
            }
        }

        if (orderBy != null && orderBy.length > 0) {
            hql.append(" order by ");
            for (String str : orderBy) {
                if (str.equals(orderBy[orderBy.length - 1])) {
                    hql.append(str);
                } else {
                    hql.append(str).append(",");
                }
            }
        }

        Query query = em.createQuery(hql.toString());

        setParameters(params, query);

        return query;
    }

    private LikeField getMatchLikeField(LikeField[] likeFields, String fieldName) {
        if (likeFields != null && likeFields.length > 0) {
            for (LikeField likeField : likeFields) {
                if (likeField.getFieldName().equals(fieldName)) {
                    return likeField;
                }
            }
        }
        return null;
    }
}
