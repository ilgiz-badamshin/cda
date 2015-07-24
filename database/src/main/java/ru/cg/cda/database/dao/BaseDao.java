package ru.cg.cda.database.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

public interface BaseDao<T> {

  void saveOrUpdate(T bean);

  T get(Serializable id);

  List<T> loadAll();

  List<T> loadAll(int from, int count);

  List<T> listByField(String fieldName, Object fieldValue);

  List<T> listByField(String fieldName, Object fieldValue, int from, int count);

  T getByField(String fieldName, Object fieldValue);

  void delete(T bean);

  List<T> loadAll(Order order);

  Number count();

  Number count(String fieldName, Object fieldValue);

  Number count(String fieldName, Object fieldValue, Map<String, String> filterMap);
}