package ru.cg.cda.admin.service;

import java.util.List;

import ru.cg.cda.admin.dto.RoleDTO;


/**
 * @author Badamshin
 */
public interface RoleService {
  RoleDTO getRole(Long roleId);

  List<RoleDTO> getRoles();

  RoleDTO saveRole(RoleDTO roleDTO);
}
