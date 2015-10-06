package ru.cg.cda.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.database.enums.UserStatus;
import ru.cg.cda.rest.dto.UserDTO;
import ru.cg.cda.rest.service.UserService;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  Logger logger = LoggerFactory.getLogger(UserController.class);

  /**
   * Возвращает пользователя по его идентификатору
   *
   * @param userId Long
   * @return UserDTO
   */
  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public UserDTO getUser(@PathVariable Long userId) {
    return userService.getUser(userId);
  }

  /**
   * Возвращает текущего пользователя
   *
   * @return UserDTO
   */
  @RequestMapping(value = "/current", method = RequestMethod.GET)
  public UserDTO getcurrentUser() {
    return userService.getUser(RestParamStorage.getCurrrentUserId());
  }

  /**
   * Возвращает всех пользователей в указанной группе
   *
   * @param groupId Long
   * @return List<UserDTO>
   */
  @RequestMapping(value = "/byGroup/{groupId}", method = RequestMethod.GET)
  public List<UserDTO> getUsersByGroup(@PathVariable Long groupId) {
    return userService.getUsersByGroup(groupId);
  }

  /**
   * Возвращает пользователей из помеченных как избранные
   *
   * @return List<UserDTO>
   */
  @RequestMapping(value = "/favorites", method = RequestMethod.GET)
  public List<UserDTO> getFavorites() {
    return userService.getFavorites();
  }

  /**
   * Добавляет пользователя в избранные
   *
   * @param favoriteId Long
   */
  @RequestMapping(value = "/favorite/{favoriteId}", method = RequestMethod.PUT)
  public void addFavorite(@PathVariable("favoriteId") Long favoriteId) {
    logger.debug("Add favoriteId {} to userId {} ", favoriteId, RestParamStorage.getCurrrentUserId());
    userService.addFavorite(favoriteId);
  }

  /**
   * Удаляет пользователя из избранного
   *
   * @param favoriteId Long
   */
  @RequestMapping(value = "/favorite/{favoriteId}", method = RequestMethod.DELETE)
  public void removeFavorite(@PathVariable("favoriteId") Long favoriteId) {
    logger.debug("Remove favoriteId {} to userId {} ", favoriteId, RestParamStorage.getCurrrentUserId());
    userService.removeFavorite(favoriteId);
  }

  /**
   * Возвращает аватарку по идентификатору пользователя
   *
   * @return FileSystemResource
   */
  @RequestMapping(value = "/status/{userId}", method = RequestMethod.GET)
  public UserStatus getStatus(@PathVariable("userId") Long userId) {
    return null;
  }

}
