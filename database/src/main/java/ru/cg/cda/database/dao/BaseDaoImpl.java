package ru.cg.cda.database.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl<T> implements BaseDao<T> {

  private Class<T> beanClass;

  @Autowired
  private SessionFactory sessionFactory;

  public BaseDaoImpl() {
  }

  @Override
  public void saveOrUpdate(T bean) {
    session().saveOrUpdate(bean);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T get(Serializable id) {
    return (T) session().get(getBeanClass(), id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> loadAll() {
    return create().list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> loadAll(final int from, final int count) {
    return create().setFirstResult(from).setMaxResults(count).list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> listByField(String fieldName, Object fieldValue) {
    return create().add(Restrictions.eq(fieldName, fieldValue)).list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> listByField(String fieldName, Object fieldValue,final int from, final int count) {
    return create().add(Restrictions.eq(fieldName, fieldValue)).setFirstResult(from).setMaxResults(count).list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public T getByField(String fieldName, Object fieldValue) {
    return (T) create().add(Restrictions.eq(fieldName, fieldValue)).uniqueResult();
  }

  @Override
  public void delete(T bean) {
    session().delete(bean);
  }

  /**
   * Get the class of the bean that is loaded, saved and deleted
   *
   * @return class of the bean
   */
  @SuppressWarnings("unchecked")
  protected final Class<T> getBeanClass() {
    if (beanClass == null) {
      ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
      Type genericType = thisType.getActualTypeArguments()[0];

      if (genericType instanceof Class) {
        beanClass = (Class<T>) genericType;
      }
      else {
        beanClass = (Class<T>) ((TypeVariable) genericType).getBounds()[0];
      }
    }

    return beanClass;
  }

  protected Criteria create() {
    return session().createCriteria(getBeanClass());
  }

  protected Session session() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> loadAll(Order order) {
    return create().addOrder(order).list();
  }

  @Override
  public Number count() {
    return (Number) create().setProjection(Projections.rowCount()).uniqueResult();
  }

  @Override
  public Number count(String fieldName, Object fieldValue) {
    return (Number) create().add(Restrictions.eq(fieldName, fieldValue)).setProjection(Projections.rowCount()).uniqueResult();
  }

  @Override
  public Number count(String fieldName, Object fieldValue, Map<String, String> filterMap) {
    Criteria c = create().add(Restrictions.eq(fieldName, fieldValue));
    for (String key : filterMap.keySet()) {
      c.add(Restrictions.ilike(key, "%" + filterMap.get(key) + "%"));
    }
    return (Number) c.setProjection(Projections.rowCount()).uniqueResult();
  }

}
