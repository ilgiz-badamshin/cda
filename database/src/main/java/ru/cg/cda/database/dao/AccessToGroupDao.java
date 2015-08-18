package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.AccessToGroup;

/**
 * @author Badamshin
 */
public interface AccessToGroupDao extends BaseDao<AccessToGroup> {
  List<Long> getGroupIds(Long roleId);

  AccessToGroup byRoleAndGroup(Long roleId, Long groupId);

  void deleteAll(Long roleId);

  void deleteAllExcept(Long roleId, List<Long> groupIds);
}
