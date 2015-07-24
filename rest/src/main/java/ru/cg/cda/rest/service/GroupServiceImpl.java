package ru.cg.cda.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Group;
import ru.cg.cda.database.dao.GroupDao;
import ru.cg.cda.rest.dto.GroupDTO;

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
    return convertGroup(groupDao.get(groupId));
  }

  public List<GroupDTO> getGroups() {
    //@TODO добавить фильтр по правам доступа
    return convertGroups(groupDao.loadAll());
  }

  private GroupDTO convertGroup(Group group) {
    GroupDTO groupDTO = new GroupDTO();
    groupDTO.setId(group.getId());
    groupDTO.setName(group.getName());
    groupDTO.setUsers(userService.convertUsers(group.getUsers()));
    return groupDTO;
  }

  public List<GroupDTO> convertGroups(List<Group> groups) {
    List<GroupDTO> result = new ArrayList<>();
    for (Group group : groups) {
      result.add(convertGroup(group));
    }
    return result;
  }

}
