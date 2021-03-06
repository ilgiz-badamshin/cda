package ru.cg.cda.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Group;
import ru.cg.cda.database.dao.GroupDao;
import ru.cg.cda.database.dao.RoleDao;
import ru.cg.cda.rest.dto.GroupDTO;
import ru.cg.cda.rest.dto.UserDTO;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class GroupServiceImpl implements GroupService {
  @Autowired
  public GroupDao groupDao;
  @Autowired
  public RoleDao roleDao;
  @Autowired
  private UserService userService;

  public GroupDTO getGroup(Long groupId) {
    List<Long> visibleGroupIds = roleDao.visibleGroupIds(RestParamStorage.getCurrrentUserId());
    return convertGroup(groupDao.get(groupId), visibleGroupIds.contains(groupId));
  }

  @Override
  public List<GroupDTO> getGroups() {
    List<Group> groups = groupDao.loadAll();
    return convertGroups(groups);
  }

  @Override
  public List<Long> invisibleIds() {
    return groupDao.invisibleIds(RestParamStorage.getCurrrentUserId());
  }


  private GroupDTO convertGroup(Group group, Boolean isVisible) {
    List<UserDTO> userDTOs = userService.convertUsers(group.getUsers());
    if (userDTOs.size() == 0) {
      return null;
    }
    GroupDTO groupDTO = new GroupDTO();
    groupDTO.setId(group.getId());
    groupDTO.setName(group.getName());
    groupDTO.setSort(group.getSort());
    groupDTO.setUsers(userDTOs);
    groupDTO.setIsVisible(isVisible);
    return groupDTO;
  }

  public List<GroupDTO> convertGroups(List<Group> groups) {
    List<GroupDTO> result = new ArrayList<>();
    List<Long> visibleGroupIds = roleDao.visibleGroupIds(RestParamStorage.getCurrrentUserId());
    for (Group group : groups) {
      GroupDTO groupDTO = convertGroup(group, visibleGroupIds.contains(group.getId()));
      if (groupDTO != null) {
        result.add(groupDTO);
      }
    }
    return result;
  }

}
