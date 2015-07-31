package ru.cg.cda.rest.service;

import java.util.Date;
import java.util.List;

import ru.cg.cda.database.bean.User;
import ru.cg.cda.rest.dto.UserDTO;

/**
 * @author Badamshin
 */
public interface UserService {
  UserDTO getUser(Long userId);

  List<UserDTO> getUsersByGroup(Long groupId);

  List<UserDTO> getFavorites();

  void addFavorite(Long favoriteId);

  void removeFavorite(Long favoriteId);

  UserDTO getAvatar(Long userId);

  List<UserDTO> changedUsers(Date updatedAt);

  List<Long> invisibleIds();

  List<Long> invisibleIds(Date updatedAt);

  List<UserDTO> visibleUsers();

  List<UserDTO> convertUsers(List<User> users);

  UserDTO convertUser(User user);

}
