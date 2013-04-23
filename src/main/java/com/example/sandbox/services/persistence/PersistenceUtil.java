package com.example.sandbox.services.persistence;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


public class PersistenceUtil implements Serializable {
    private static final long serialVersionUID=-276417828563635020L;
    
    
    @Inject
    @FesPackEntityManager
    private EntityManager entityManager;
    
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    public <T>void persist(  final T entity){
        //getEntityManager().joinTransaction();
        getEntityManager().persist(entity);
    }
    
    public <T>void remove(  final T entity) throws NoResultException {
        //getEntityManager().joinTransaction();
        getEntityManager().remove(entity);
    }
    
    public <T>T removeById(  final Class<T> type,  final Long id) throws NoResultException {
        T object=findById(type,id);
        remove(object);
        return object;
    }
    @SuppressWarnings("unchecked") public <T>T findById(  final Class<T> type,  final Long id) throws NoResultException {
        Class<?> clazz=getObjectClass(type);
        T result=(T)getEntityManager().find(clazz,id);
        if (result == null) {
            throw new NoResultException("No object of type: " + type + " with ID: "+ id);
        }
        return result;
    }

    @SuppressWarnings("unchecked") public <T>T findById(  final Class<T> type,  final String id) throws NoResultException {
        Class<?> clazz=getObjectClass(type);
        T result=(T)getEntityManager().find(clazz,id);
        if (result == null) {
            throw new NoResultException("No object of type: " + type + " with ID: "+ id);
        }
        return result;
    }
    
    public <T>T merge(  final T entity){
        //getEntityManager().joinTransaction();
        return getEntityManager().merge(entity);
    }
   
    public <T>void refresh(  final T entity){
        getEntityManager().refresh(entity);
    }
    public Class<?> getObjectClass(  final Object type) throws IllegalArgumentException {
        Class<?> clazz=null;
        if (type == null) {
            throw new IllegalArgumentException("Null has no type. You must pass an Object");
        }
        else     if (type instanceof Class<?>) {
            clazz=(Class<?>)type;
        }
        else {
            clazz=type.getClass();
        }
        return clazz;
    }
    
    @SuppressWarnings("unchecked") public <T>List<T> findByNamedQuery(  final String namedQueryName){
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }
   
    @SuppressWarnings("unchecked") public <T>List<T> findByNamedQuery(  final String namedQueryName,  final Object... params){
        Query query=getEntityManager().createNamedQuery(namedQueryName);
        int i=1;
        for (    Object p : params) {
            query.setParameter(i,p);
            i++;
        }
        return query.getResultList();
    }
    
    public <T>List<T> findAll(  final Class<T> type){
        CriteriaQuery<T> query=getEntityManager().getCriteriaBuilder().createQuery(type);
        query.from(type);
        return getEntityManager().createQuery(query).getResultList();
    }
    
    @SuppressWarnings("unchecked") public <T>T findUniqueByNamedQuery(  final String namedQueryName) throws NoResultException {
        return (T)getEntityManager().createNamedQuery(namedQueryName).getSingleResult();
    }
    
    @SuppressWarnings("unchecked") public <T>T findUniqueByNamedQuery(  final String namedQueryName,  final Object... params) throws NoResultException {
        Query query=getEntityManager().createNamedQuery(namedQueryName);
        int i=1;
        for (    Object p : params) {
            query.setParameter(i,p);
            i++;
        }
        return (T)query.getSingleResult();
    }
    public <T>int count(  Class<T> type){
        CriteriaQuery<Long> cq=getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        javax.persistence.criteria.Root<T> rt=cq.from(type);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q=getEntityManager().createQuery(cq);
        return ((Long)q.getSingleResult()).intValue();
    }
    
    @SuppressWarnings("unchecked") public <T>List<T> findAll(  Class<T> type,  int firstResult,  int maxResults){
        CriteriaQuery<T> cq=getEntityManager().getCriteriaBuilder().createQuery(type);
        cq.select(cq.from(type));
        javax.persistence.Query q=getEntityManager().createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }
    

    
}
