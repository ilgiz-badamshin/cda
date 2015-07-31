package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.Role;

/**
 * @author Badamshin
 */
public interface RoleDao extends BaseDao<Role> {
  List<Long> visibleGroupIds(Long userId);

  List<Long> visibleUserIds(Long userId);
}
