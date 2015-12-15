package ru.cg.cda.admin.service;

import java.io.File;
import java.util.List;

import ru.cg.cda.admin.dto.UserDTO;
import ru.cg.cda.database.bean.User;

/**
 * @author Badamshin
 */
public interface UserService {
  List<UserDTO> getUsers();

  UserDTO getUser(Long userId);

  List<Long> getUserRoleIds(Long userId);

  List<UserDTO> getUsersByGroup(Long groupId);

  List<UserDTO> convertUsers(List<User> users);

  UserDTO convertUser(User user);

  void setGroup(Long userId, Long groupId);

  void setOrgName(Long userId, String orgName);

  void setPositionName(Long userId, String positionName);

  void setSort(Long userId, Long sort);

  void saveAvatar(Long userId, String avatar);

  Boolean deleteAvatar(Long userId);

  File getAvatar(Long userId);
}
