package ru.cg.cda.rest.controller;

import java.util.List;

import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.rest.dto.GroupDTO;

/**
 * @author Badamshin
 */
@Api
@RestController
@RequestMapping("/rest/group")
public class GroupController {

  /**
   * Возвращает список групп
   *
   * @return List<GroupDTO>
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<GroupDTO> getGroups(Boolean withUsers) {
    return null;
  }

  /**
   * Возвращает группу по его идентификатору
   *
   * @param groupId Long
   * @return GroupDTO
   */
  @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
  public GroupDTO getGroup(@PathVariable Long groupId) {
    return null;
  }
}
