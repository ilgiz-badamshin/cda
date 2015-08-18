package ru.cg.cda.rest.service;

import java.io.File;
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

  List<Long> invisibleIds();

  List<UserDTO> convertUsers(List<User> users);

  UserDTO convertUser(User user);

  File getAvatar(Long userId);
}
