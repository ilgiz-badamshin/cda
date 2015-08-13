package ru.cg.cda.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.admin.dto.GroupDTO;
import ru.cg.cda.database.bean.Group;
import ru.cg.cda.database.dao.GroupDao;
import ru.cg.cda.database.dao.RoleDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class GroupServiceImpl implements GroupService {
  @Autowired
  public GroupDao groupDao;
  @Autowired
  private ParamsService paramsService;

  public GroupDTO getGroup(Long groupId) {
    return convertGroup(groupDao.get(groupId));
  }

  @Override
  public List<GroupDTO> getGroups() {
    List<Group> groups = groupDao.loadAll();
    return convertGroups(groups);
  }

  @Override
  public GroupDTO saveGroup(GroupDTO groupDTO) {
    Group group;
    if (groupDTO.getId() == null) {
      group = new Group();
    }
    else {
      group = groupDao.get(groupDTO.getId());
    }
    group.setName(groupDTO.getName());
    groupDao.saveOrUpdate(group);
    paramsService.increaseDbVersion();
    return convertGroup(group);
  }

  @Override
  public GroupDTO convertGroup(Group group) {
    if (group == null) {
      return null;
    }
    GroupDTO groupDTO = new GroupDTO();
    groupDTO.setId(group.getId());
    groupDTO.setName(group.getName());
    return groupDTO;
  }

  @Override
  public List<GroupDTO> convertGroups(List<Group> groups) {
    List<GroupDTO> result = new ArrayList<>();
    for (Group group : groups) {
      GroupDTO groupDTO = convertGroup(group);
      if (groupDTO != null) {
        result.add(groupDTO);
      }
    }
    return result;
  }

}
