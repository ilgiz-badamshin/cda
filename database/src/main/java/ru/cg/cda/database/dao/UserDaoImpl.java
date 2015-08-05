package ru.cg.cda.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
    return (User) create().add(Restrictions.eq("udsId", udsId).ignoreCase()).uniqueResult();
  }

  @Override
  public List<Long> getInvisibleIds(Long userId) {
    return new ArrayList<>();
  }

  @Override
  public Integer deleteAll() {
    Query query = session().createQuery("update User set deleted = true where deleted != true");
    return query.executeUpdate();
  }

  @Override
  public Integer deleteAllExcept(List<Long> skipIds) {
    Query query = session().createQuery("update User set deleted = true where deleted != true AND id  NOT IN :skipIds ");
    query.setParameterList("skipIds", skipIds);
    return query.executeUpdate();
  }
}
