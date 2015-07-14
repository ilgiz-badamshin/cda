package ru.cg.cda.rest.controller;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.rest.dto.UserDTO;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {

  /**
   * Возвращает пользователя по его идентификатору
   *
   * @param userId Long
   * @return UserDTO
   */
  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public UserDTO getUser(@PathVariable Long userId) {
    return null;
  }

  /**
   * Возвращает всех пользователей в указанной группе
   *
   * @param groupId Long
   * @return List<UserDTO>
   */
  @RequestMapping(value = "/byGroup/{groupId}", method = RequestMethod.GET)
  public List<UserDTO> getUsersByGroup(@PathVariable Long groupId) {
    return null;
  }

  /**
   * Возвращает пользователей из помеченных как избранные
   *
   * @return List<UserDTO>
   */
  @RequestMapping(value = "/favorites", method = RequestMethod.GET)
  public List<UserDTO> getFavorites() {
    return null;
  }

  /**
   * Добавляет пользователя в избранные
   *
   * @param userId Long
   * @return Boolean
   */
  @RequestMapping(value = "/favorite/{userId}", method = RequestMethod.PUT)
  public Boolean addFavorite(@PathVariable("userId") Long userId) {
    return null;
  }

  /**
   * Удаляет пользователя из избранного
   *
   * @param userId Long
   * @return Boolean
   */
  @RequestMapping(value = "/favorite/{userId}", method = RequestMethod.DELETE)
  public Boolean removeFavorite(@PathVariable("userId") Long userId) {
    return null;
  }

  /**
   * Возвращает аватарку по идентификатору пользователя
   *
   * @return FileSystemResource
   */
  @RequestMapping(value = "/avatar/{userId}", method = RequestMethod.GET)
  public FileSystemResource getAvatar(@PathVariable("userId") Long userId) {
    return null;
  }

}
