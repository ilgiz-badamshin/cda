package ru.cg.cda.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.AccessToGroup;
import ru.cg.cda.database.dao.AccessToGroupDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class AccessToGroupServiceImpl implements AccessToGroupService {
  @Autowired
  private AccessToGroupDao accessToGroupDao;
  @Autowired
  private ParamsService paramsService;

  @Override
  public List<Long> getRoleGroupIds(Long roleId) {
    return accessToGroupDao.getGroupIds(roleId);
  }

  @Override
  public void addGroup(Long roleId, Long groupId) {
    AccessToGroup accessToGroup = accessToGroupDao.byRoleAndGroup(roleId, groupId);
    if (accessToGroup == null) {
      accessToGroup = new AccessToGroup();
      accessToGroup.setRoleId(roleId);
      accessToGroup.setGroupId(groupId);
      accessToGroupDao.saveOrUpdate(accessToGroup);
      paramsService.increaseDbVersion();
    }
  }

  @Override
  public void removeGroup(Long roleId, Long groupId) {
    AccessToGroup accessToGroup = accessToGroupDao.byRoleAndGroup(roleId, groupId);
    if (accessToGroup != null) {
      accessToGroupDao.delete(accessToGroup);
      paramsService.increaseDbVersion();
    }
  }
}
