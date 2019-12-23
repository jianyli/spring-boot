package com.li.support.jpaUtil.data.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import com.google.common.collect.Lists;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> entityClass, EntityManager entityManager) {
        super(entityClass, entityManager);
        this.entityManager = entityManager;
    }
/*
    public List<T> findListByCondition() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) criteriaBuilder.createQuery(this.getQueryClass());
        Root<T> itemRoot = query.from(getQueryClass());
        List<Predicate> predicates = Lists.newArrayList();
        //查询语句
        query.where( predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        List<T> result = typedQuery.getResultList();
        return result;
    }
*/
    public Class getQueryClass() {
        return null;
    }
}
