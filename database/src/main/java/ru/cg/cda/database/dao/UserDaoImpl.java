package ru.cg.cda.database.dao;

import java.util.ArrayList;
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
}
