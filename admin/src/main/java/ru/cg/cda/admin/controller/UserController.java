package ru.cg.cda.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.admin.dto.UserDTO;
import ru.cg.cda.admin.service.LinkUserRoleService;
import ru.cg.cda.admin.service.ParamsService;
import ru.cg.cda.admin.service.UserService;


/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private LinkUserRoleService linkUserRoleService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<UserDTO> getUsers() {
    return userService.getUsers();
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public UserDTO getUser(@PathVariable Long userId) {
    return userService.getUser(userId);
  }

  @RequestMapping(value = "/{userId}/roleIds", method = RequestMethod.GET)
  public List<Long> getUserRoles(@PathVariable Long userId) {
    return userService.getUserRoleIds(userId);
  }

  @RequestMapping(value = "/byGroup/{groupId}", method = RequestMethod.GET)
  public List<UserDTO> getUsersByGroup(@PathVariable Long groupId) {
    return userService.getUsersByGroup(groupId);
  }

  @RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.PUT)
  public void addRole(@PathVariable Long userId, @PathVariable Long roleId) {
    linkUserRoleService.addRole(userId, roleId);
  }

  @RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.DELETE)
  public void removeRole(@PathVariable Long userId, @PathVariable Long roleId) {
    linkUserRoleService.removeRole(userId, roleId);
  }

  @RequestMapping(value = "/{userId}/group/{groupId}", method = RequestMethod.PUT)
  public void setGroupId(@PathVariable Long userId, @PathVariable Long groupId) {
    userService.setGroup(userId, groupId);
  }
}
