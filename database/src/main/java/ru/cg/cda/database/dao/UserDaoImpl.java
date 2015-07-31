package ru.cg.cda.database.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.User;

/**
 * @author Badamshin
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
  @Override
  public List<User> favoriteUsers(Long userId) {
    Criteria criteria = create();
    criteria.createCriteria("favorites", "favorites");
    criteria.add(Restrictions.eq("id", userId));
    User user = (User) criteria.uniqueResult();
    if (user == null) {
      return new ArrayList<>();
    }
    return user.getFavorites();
  }

  @Override
  public User getByUdsId(String udsId) {
    return getByField("udsId", udsId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> changedUsers(Long userId, Date updatedAt) {
    Criteria criteria = create();
    //@TODO ограничить видимость пользователей
//    criteria.add(Restrictions.eq("id", userId));
    criteria.add(Restrictions.ge("updatedAt", updatedAt));
    return criteria.list();
  }

  public List<Long> getInvisibleIds(Long userId) {
    return new ArrayList<>();
  }

  public List<Long> getInvisibleIds(Long userId, Date updatedAt) {
    return new ArrayList<>();
  }

  @SuppressWarnings("unchecked")
  public List<User> visibleUsers(Long userId) {
    //@TODO ограничить видимость пользователей
    return loadAll();
  }
}
