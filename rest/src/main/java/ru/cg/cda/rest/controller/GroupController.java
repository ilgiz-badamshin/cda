package ru.cg.cda.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.rest.dto.GroupDTO;
import ru.cg.cda.rest.service.GroupService;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/group")
public class GroupController {

  @Autowired
  private GroupService groupService;

  /**
   * Возвращает список групп
   *
   * @return List<GroupDTO>
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<GroupDTO> getGroups() {
    return groupService.getGroups();
  }

  /**
   * Возвращает группу по его идентификатору
   *
   * @param groupId Long
   * @return GroupDTO
   */
  @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
  public GroupDTO getGroup(@PathVariable Long groupId) {
    return groupService.getGroup(groupId);
  }
}
