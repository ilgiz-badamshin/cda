package ru.cg.cda.admin.service;

import java.util.List;

/**
 * @author Badamshin
 */
public interface AccessToGroupService {

  List<Long> getRoleGroupIds(Long roleId);

  void addGroup(Long roleId, Long groupId);

  void removeGroup(Long roleId, Long groupId);
}
