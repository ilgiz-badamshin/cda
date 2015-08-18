package ru.cg.cda.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.admin.dto.RoleDTO;
import ru.cg.cda.database.bean.AccessToGroup;
import ru.cg.cda.database.bean.Group;
import ru.cg.cda.database.bean.Role;
import ru.cg.cda.database.dao.AccessToGroupDao;
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
  private AccessToGroupDao accessToGroupDao;
  @Autowired
  private ParamsService paramsService;

  @Override
  public RoleDTO getRole(Long roleId, boolean withGroupIds) {
    return convertRole(roleDao.get(roleId), withGroupIds);
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

  public void saveRoleGroups(Long roleId, List<Long> groupIds) {
    if (groupIds.size() > 0) {
      accessToGroupDao.deleteAllExcept(roleId, groupIds);
    }
    else {
      accessToGroupDao.deleteAll(roleId);
    }
    for (Long groupId : groupIds) {
      AccessToGroup accessToGroup = accessToGroupDao.byRoleAndGroup(roleId, groupId);
      if (accessToGroup == null) {
        accessToGroup = new AccessToGroup();
        accessToGroup.setRoleId(roleId);
        accessToGroup.setGroupId(groupId);
        accessToGroupDao.saveOrUpdate(accessToGroup);
      }
    }
  }

  private RoleDTO convertRole(Role role) {
    return convertRole(role, false);
  }

  private RoleDTO convertRole(Role role, boolean withGroupIds) {
    RoleDTO roleDTO = new RoleDTO();
    roleDTO.setId(role.getId());
    roleDTO.setName(role.getName());
    if (withGroupIds) {
      List<Long> groupIds = new ArrayList<>();
      for (Group group : role.getGroups()) {
        groupIds.add(group.getId());
      }
      roleDTO.setGroupIds(groupIds);
    }
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
