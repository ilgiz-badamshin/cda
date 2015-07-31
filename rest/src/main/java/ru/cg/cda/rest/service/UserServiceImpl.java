package ru.cg.cda.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Favorite;
import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.FavoriteDao;
import ru.cg.cda.database.dao.UserDao;
import ru.cg.cda.rest.dto.UserDTO;
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
  private FavoriteDao favoriteDao;

  public UserDTO getUser(Long userId) {
    return convertUser(userDao.get(userId));
  }

  public List<UserDTO> getUsersByGroup(Long groupId) {
    return convertUsers(userDao.listByField("groupId", groupId));
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

  public UserDTO getAvatar(Long userId) {
    return null;  //TODO: implement this method
  }

  @Override
  public List<UserDTO> changedUsers(Date updatedAt) {
    List<User> users = userDao.changedUsers(RestParamStorage.getCurrrentUserId(), updatedAt);
    return convertUsers(users);
  }

  @Override
  public List<UserDTO> visibleUsers() {
    List<User> users = userDao.visibleUsers(RestParamStorage.getCurrrentUserId());
    return convertUsers(users);
  }

  @Override
  public List<Long> invisibleIds() {
    return userDao.getInvisibleIds(RestParamStorage.getCurrrentUserId());
  }

  @Override
  public List<Long> invisibleIds(Date updatedAt) {
    return userDao.getInvisibleIds(RestParamStorage.getCurrrentUserId(), updatedAt);
  }

  public UserDTO convertUser(User user) {
    if (user == null) {
      return null;
    }
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setGroupId(user.getGroupId());
    userDTO.setLastName(user.getLastName());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setMiddleName(user.getMiddleName());
    userDTO.setVksNumber(user.getVksNumber());
    userDTO.setMobilePhone(user.getMobilePhone());
    userDTO.setWorkPhone(user.getWorkPhone());
    userDTO.setOrgName(user.getOrgName());
    userDTO.setPositionName(user.getPositionName());

    userDTO.setAvatarUrl("/rest/user/avatar/" + user.getId().toString());
    //@TODO сделать методы
    userDTO.setIsFavorite(favoriteDao.isFavorite(RestParamStorage.getCurrrentUserId(), user.getId()));
//    userDTO.setAccessLevel(user.getFirstName());
    return userDTO;
  }


  public List<UserDTO> convertUsers(List<User> users) {
    List<UserDTO> result = new ArrayList<>();
    for (User user : users) {
      result.add(convertUser(user));
    }
    return result;
  }
}
