package ru.cg.cda.admin.service;

import java.util.List;

import ru.cg.cda.admin.dto.GroupDTO;
import ru.cg.cda.database.bean.Group;


/**
 * @author Badamshin
 */
public interface GroupService {
  GroupDTO getGroup(Long groupId);

  List<GroupDTO> getGroups();

  GroupDTO saveGroup(GroupDTO groupDTO);

  GroupDTO convertGroup(Group group);

  List<GroupDTO> convertGroups(List<Group> groups);
}
