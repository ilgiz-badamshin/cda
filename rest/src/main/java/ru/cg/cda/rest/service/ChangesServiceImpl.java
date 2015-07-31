package ru.cg.cda.rest.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.cg.cda.rest.dto.ChangesDto;

/**
 * @author Badamshin
 */
@Component
@Transactional
public class ChangesServiceImpl implements ChangesService {

  @Autowired
  UserService userService;

  @Autowired
  GroupService groupService;

  public ChangesDto getChanges(Date updatedAt) {
    ChangesDto changesDto = new ChangesDto();
    boolean totalReload = false;
    if (totalReload) {
      changesDto.setGroups(groupService.visibleGroups());
      changesDto.setDeletedGroupIds(groupService.invisibleIds());
    }
    else {
      changesDto.setGroups(groupService.visibleGroups(updatedAt));
      changesDto.setDeletedGroupIds(groupService.invisibleIds(updatedAt));
    }
    if (totalReload) {
      changesDto.setUsers(userService.visibleUsers());
      changesDto.setDeletedUserdIds(userService.invisibleIds());
    }
    else {
      changesDto.setUsers(userService.changedUsers(updatedAt));
      changesDto.setDeletedUserdIds(userService.invisibleIds(updatedAt));
    }
    changesDto.setUpdatedAt(new Date());
    return changesDto;
  }
}
