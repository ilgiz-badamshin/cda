package ru.cg.cda.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.admin.dto.RoleDTO;
import ru.cg.cda.database.bean.Role;
import ru.cg.cda.database.dao.RoleDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  public RoleDao roleDao;
  @Autowired
  private ParamsService paramsService;

  @Override
  public RoleDTO getRole(Long roleId) {
    return convertRole(roleDao.get(roleId));
  }

  @Override
  public List<RoleDTO> getRoles() {
    return convertRoles(roleDao.loadAll());
  }

  @Override
  public RoleDTO saveRole(RoleDTO roleDTO) {
    Role role;
    if (roleDTO.getId() == null) {
      role = new Role();
    }
    else {
      role = roleDao.get(roleDTO.getId());
    }
    role.setName(roleDTO.getName());
    roleDao.saveOrUpdate(role);
    paramsService.increaseDbVersion();
    return convertRole(role);
  }

  private RoleDTO convertRole(Role role) {
    RoleDTO roleDTO = new RoleDTO();
    roleDTO.setId(role.getId());
    roleDTO.setName(role.getName());
    return roleDTO;
  }

  public List<RoleDTO> convertRoles(List<Role> roles) {
    List<RoleDTO> result = new ArrayList<>();
    for (Role role : roles) {
      RoleDTO roleDTO = convertRole(role);
      if (roleDTO != null) {
        result.add(roleDTO);
      }
    }
    return result;
  }

}
