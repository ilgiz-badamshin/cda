package ru.cg.cda.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Group;
import ru.cg.cda.database.dao.GroupDao;
import ru.cg.cda.rest.dto.GroupDTO;
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
  private UserService userService;

  public GroupDTO getGroup(Long groupId) {
    //@TODO добавить фильтр по правам доступа
    return convertGroup(groupDao.get(groupId), true);
  }

  @Override
  public List<GroupDTO> getGroups() {
    List<Group> groups = groupDao.visibleGroups(RestParamStorage.getCurrrentUserId(), null);
    return convertGroups(groups, true);
  }

  @Override
  public List<GroupDTO> visibleGroups() {
    return visibleGroups(null);
  }

  @Override
  public List<GroupDTO> visibleGroups(Date updatedAt) {
    List<Group> groups = groupDao.visibleGroups(RestParamStorage.getCurrrentUserId(), updatedAt);
    return convertGroups(groups, false);
  }

  public List<Long> invisibleIds() {
    return invisibleIds(null);
  }

  public List<Long> invisibleIds(Date updatedAt) {
    List<Long> result = groupDao.getInvisibleIds(RestParamStorage.getCurrrentUserId(), updatedAt);
    return result;
  }

  private GroupDTO convertGroup(Group group, Boolean withUser) {
    GroupDTO groupDTO = new GroupDTO();
    groupDTO.setId(group.getId());
    groupDTO.setName(group.getName());
    //@TODO вынести это отсюда?
    if (withUser) {
      groupDTO.setUsers(userService.convertUsers(group.getUsers()));
    }
    return groupDTO;
  }

  public List<GroupDTO> convertGroups(List<Group> groups, Boolean withUser) {
    List<GroupDTO> result = new ArrayList<>();
    for (Group group : groups) {
      result.add(convertGroup(group, withUser));
    }
    return result;
  }

}
