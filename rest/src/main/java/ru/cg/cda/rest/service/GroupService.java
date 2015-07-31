package ru.cg.cda.rest.service;

import java.util.Date;
import java.util.List;

import ru.cg.cda.rest.dto.GroupDTO;

/**
 * @author Badamshin
 */
public interface GroupService {
  GroupDTO getGroup(Long groupId);

  List<GroupDTO> getGroups();

  List<GroupDTO> visibleGroups();

  List<GroupDTO> visibleGroups(Date updatedAt);

  List<Long> invisibleIds();

  List<Long> invisibleIds(Date updatedAt);
}
