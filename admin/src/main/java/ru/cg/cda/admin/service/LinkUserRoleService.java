package ru.cg.cda.admin.service;

/**
 * @author Badamshin
 */
public interface LinkUserRoleService {

  void addRole(Long userId, Long roleId);

  void removeRole(Long userId, Long roleId);
}
