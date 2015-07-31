package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.User;

/**
 * @author Badamshin
 */
public interface UserDao extends BaseDao<User> {
  List<User> favoriteUsers(Long userId);

  User getByUdsId(String udsId);

  List<Long> getInvisibleIds(Long userId);
}
