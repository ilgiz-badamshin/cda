package ru.cg.cda.admin.service;

import java.util.List;

import ru.cg.cda.admin.dto.UserRoleDTO;

/**
 * @author Badamshin
 */
public interface UserRoleService {
  List<UserRoleDTO> getByUser(Long userId);

}
