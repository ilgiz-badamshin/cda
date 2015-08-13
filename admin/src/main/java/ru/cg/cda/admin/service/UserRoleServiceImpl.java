package ru.cg.cda.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.admin.dto.UserRoleDTO;
import ru.cg.cda.database.bean.LinkUserRole;
import ru.cg.cda.database.dao.LinkUserRoleDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class UserRoleServiceImpl implements UserRoleService {
  @Autowired
  private LinkUserRoleDao linkUserRoleDao;

  @Override
  public List<UserRoleDTO> getByUser(Long userId) {
    return convertLinkUserRoles(linkUserRoleDao.listByField("userId", userId));
  }

  public UserRoleDTO convertLinkUserRole(LinkUserRole linkUserRole) {
    if (linkUserRole == null) {
      return null;
    }
    UserRoleDTO userRoleDTO = new UserRoleDTO();
    userRoleDTO.setId(linkUserRole.getId());
    userRoleDTO.setUserId(linkUserRole.getUserId());
    userRoleDTO.setRoleId(linkUserRole.getRoleId());
    userRoleDTO.setRoleId(linkUserRole.getRoleId());
    return userRoleDTO;
  }


  public List<UserRoleDTO> convertLinkUserRoles(List<LinkUserRole> linkUserRoles) {
    List<UserRoleDTO> result = new ArrayList<>();
    for (LinkUserRole linkUserRole : linkUserRoles) {
      result.add(convertLinkUserRole(linkUserRole));
    }
    return result;
  }
}
