package ru.cg.cda.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.cg.cda.admin.dto.GroupDTO;
import ru.cg.cda.admin.service.GroupService;


/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/group")
public class GroupController {

  @Autowired
  private GroupService groupService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<GroupDTO> getGroups() {
    return groupService.getGroups();
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public GroupDTO saveGroup(@RequestBody GroupDTO groupDTO) {
    return groupService.saveGroup(groupDTO);
  }

  @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
  public GroupDTO getGroup(@PathVariable Long groupId) {
    return groupService.getGroup(groupId);
  }
}
