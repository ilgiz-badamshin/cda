package ru.cg.cda.database.dao;

import java.util.Date;
import java.util.List;

import ru.cg.cda.database.bean.User;

/**
 * @author Badamshin
 */
public interface UserDao extends BaseDao<User> {
  List<User> favoriteUsers(Long userId);

  User getByUdsId(String udsId);

  List<User> changedUsers(Long userId, Date updatedAt);

  List<Long> getInvisibleIds(Long userId);

  List<Long> getInvisibleIds(Long userId, Date updatedAt);

  List<User> visibleUsers(Long userId);
}
