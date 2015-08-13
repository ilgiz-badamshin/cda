package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.LinkUserRole;

/**
 * @author Badamshin
 */
public interface LinkUserRoleDao extends BaseDao<LinkUserRole> {
  List<Long> getRoleIds(Long userId);

  LinkUserRole byUserAndRole(Long userId, Long roleId);
}
