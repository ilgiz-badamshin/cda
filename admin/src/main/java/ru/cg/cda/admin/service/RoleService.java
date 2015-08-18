package ru.cg.cda.admin.service;

import java.util.List;

import ru.cg.cda.admin.dto.RoleDTO;


/**
 * @author Badamshin
 */
public interface RoleService {
  RoleDTO getRole(Long roleId, boolean withGroupIds);

  List<RoleDTO> getRoles();

  RoleDTO saveRole(RoleDTO roleDTO);

  void saveRoleGroups(Long roleId, List<Long> groupIds);
}
