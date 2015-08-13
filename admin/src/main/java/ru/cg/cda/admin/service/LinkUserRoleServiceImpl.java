package ru.cg.cda.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.LinkUserRole;
import ru.cg.cda.database.dao.LinkUserRoleDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class LinkUserRoleServiceImpl implements LinkUserRoleService {
  @Autowired
  public LinkUserRoleDao linkUserRoleDao;
  @Autowired
  private ParamsService paramsService;

  @Override
  public void addRole(Long userId, Long roleId) {
    LinkUserRole linkUserRole = linkUserRoleDao.byUserAndRole(userId, roleId);
    if (linkUserRole == null) {
      linkUserRole = new LinkUserRole();
      linkUserRole.setUserId(userId);
      linkUserRole.setRoleId(roleId);
      linkUserRoleDao.saveOrUpdate(linkUserRole);
      paramsService.increaseDbVersion();
    }
  }

  @Override
  public void removeRole(Long userId, Long roleId) {
    LinkUserRole linkUserRole = linkUserRoleDao.byUserAndRole(userId, roleId);
    if (linkUserRole != null) {
      linkUserRoleDao.delete(linkUserRole);
      paramsService.increaseDbVersion();
    }
  }
}
