package ru.cg.cda.admin.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.cg.cda.admin.dto.UserDTO;
import ru.cg.cda.admin.service.LinkUserRoleService;
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

  @RequestMapping(value = "/{userId}/group", method = RequestMethod.DELETE)
  public void removeGroupId(@PathVariable Long userId) {
    userService.setGroup(userId, null);
  }

  @RequestMapping(value = "/{userId}/orgName", method = RequestMethod.POST)
  public void setOrgName(@PathVariable Long userId, @RequestBody String orgName) {
    userService.setOrgName(userId, orgName);
  }

  @RequestMapping(value = "/{userId}/positionName", method = RequestMethod.POST)
  public void setPositionName(@PathVariable Long userId, @RequestBody String positionName) {
    userService.setPositionName(userId, positionName);
  }

  @RequestMapping(value = "/{userId}/sort", method = RequestMethod.POST)
  public void setSort(@PathVariable Long userId, @RequestBody Long sort) {
    userService.setSort(userId, sort);
  }

  @RequestMapping(value = "/{userId}/avatar", method = RequestMethod.DELETE)
  public Boolean setAvatar(@PathVariable Long userId) {
    return userService.deleteAvatar(userId);
  }

  @RequestMapping(value = "/{userId}/avatar", method = RequestMethod.POST)
  public void setAvatar(@PathVariable Long userId, @RequestBody String image) {
    userService.saveAvatar(userId, image);
  }

  @RequestMapping(value = "/{userId}/avatar", method = RequestMethod.GET)
  public void getAvatar(@PathVariable("userId") Long userId, HttpServletResponse response) {
    try {
      InputStream inputStream = new FileInputStream(userService.getAvatar(userId));
      IOUtils.copy(inputStream, response.getOutputStream());
      response.flushBuffer();
    }
    catch (IOException ex) {
      throw new RuntimeException("IOError writing file to output stream");
    }
  }
}
