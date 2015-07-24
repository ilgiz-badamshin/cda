package ru.cg.cda.rest.service;

import java.util.List;

import ru.cg.cda.rest.dto.GroupDTO;

/**
 * @author Badamshin
 */
public interface GroupService {
  GroupDTO getGroup(Long groupId);

  List<GroupDTO> getGroups();
}
