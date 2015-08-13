package ru.cg.cda.rest.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Favorite;
import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.FavoriteDao;
import ru.cg.cda.database.dao.RoleDao;
import ru.cg.cda.database.dao.UserDao;
import ru.cg.cda.rest.dto.UserDTO;
import ru.cg.cda.rest.exception.GroupAccessException;
import ru.cg.cda.rest.exception.UserAccessException;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;
  @Autowired
  private RoleDao roleDao;
  @Autowired
  private FavoriteDao favoriteDao;

  public UserDTO getUser(Long userId) {
    List<Long> visibleIds = roleDao.visibleUserIds(userId);
    if (!Objects.equals(userId, RestParamStorage.getCurrrentUserId()) && !visibleIds.contains(userId)) {
      throw new UserAccessException("Нет прав на просмотр деталей пользователя");
    }
    return convertUser(userDao.get(userId));
  }

  public List<UserDTO> getUsersByGroup(Long groupId) {
    List<Long> visibleIds = roleDao.visibleGroupIds(RestParamStorage.getCurrrentUserId());
    if (!visibleIds.contains(groupId)) {
      throw new GroupAccessException("Нет доступа к группе");
    }
    return convertUsers(userDao.byGroup(groupId));
  }

  public List<UserDTO> getFavorites() {
    Long userId = RestParamStorage.getCurrrentUserId();
    return convertUsers(userDao.favoriteUsers(userId));
  }

  public void addFavorite(Long favoriteId) {
    Favorite favorite = favoriteDao.get(RestParamStorage.getCurrrentUserId(), favoriteId);
    if (favorite == null) {
      favorite = new Favorite();
      favorite.setUserId(RestParamStorage.getCurrrentUserId());
      favorite.setFavoriteId(favoriteId);
      favoriteDao.saveOrUpdate(favorite);
    }
  }

  public void removeFavorite(Long favoriteId) {
    Favorite favorite = favoriteDao.get(RestParamStorage.getCurrrentUserId(), favoriteId);
    if (favorite != null) {
      favoriteDao.delete(favorite);
    }
  }

  public InputStream getAvatar(Long userId) {
    return getClass().getClassLoader().getResourceAsStream(String.format("ru/cg/cda/rest/avatar/%s.jpg", userId));
  }

  @Override
  public List<Long> invisibleIds() {
    return userDao.getInvisibleIds(RestParamStorage.getCurrrentUserId());
  }

  public UserDTO convertUser(User user) {
    if (user == null) {
      return null;
    }
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setGroupId(user.getGroupId());
    userDTO.setUserName(user.getUserName());
    userDTO.setUserUri(user.getUserName() + "@demo.local");
    userDTO.setFirstName(user.getFirstName());
    userDTO.setMiddleName(user.getMiddleName());
    userDTO.setVksNumber(user.getVksNumber());
    userDTO.setMobilePhone(user.getMobilePhone());
    userDTO.setWorkPhone(user.getWorkPhone());
    userDTO.setOrgName(user.getOrgName());
    userDTO.setPositionName(user.getPositionName());

    //@TODO убрать ИП из адреса
    userDTO.setAvatarUrl("http://10.10.18.34:8080/rest/public/avatar/" + user.getId().toString());
    //@TODO сделать методы
    userDTO.setIsFavorite(favoriteDao.isFavorite(RestParamStorage.getCurrrentUserId(), user.getId()));
    return userDTO;
  }


  public List<UserDTO> convertUsers(List<User> users) {
    List<UserDTO> result = new ArrayList<>();
    for (User user : users) {
      if (Objects.equals(user.getId(), RestParamStorage.getCurrrentUserId())) {
        continue;
      }
      result.add(convertUser(user));
    }
    return result;
  }
}
