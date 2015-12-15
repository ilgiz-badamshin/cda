package ru.cg.cda.rest.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Favorite;
import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.FavoriteDao;
import ru.cg.cda.database.dao.RoleDao;
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
  private RoleDao roleDao;
  @Autowired
  private FavoriteDao favoriteDao;
  @Value("${avatarsFolder}")
  private String AVATARS_FOLDER;
  @Value("${cm.domen}")
  private String CM_DOMEN;

  public UserDTO getUser(Long userId) {
    List<Long> visibleUserIds = roleDao.visibleUserIds(RestParamStorage.getCurrrentUserId());
    Boolean isVisible = visibleUserIds.contains(userId) && !Objects.equals(userId, RestParamStorage.getCurrrentUserId());
    return convertUser(userDao.get(userId), isVisible);
  }

  public List<UserDTO> getUsersByGroup(Long groupId) {
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

  public File getAvatar(Long userId) {
    return new File(AVATARS_FOLDER, userId + ".png");
  }

  @Override
  public List<Long> invisibleIds() {
    return userDao.getInvisibleIds(RestParamStorage.getCurrrentUserId());
  }

  public UserDTO convertUser(User user, Boolean isVisible) {
    if (user == null) {
      return null;
    }
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setGroupId(user.getGroupId());
    userDTO.setUserName(user.getUserName());
    userDTO.setUserUri(user.getUserName() + CM_DOMEN);
    userDTO.setLastName(user.getLastName());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setMiddleName(user.getMiddleName());
    userDTO.setVksNumber(user.getVksNumber());
    userDTO.setMobilePhone(user.getMobilePhone());
    userDTO.setWorkPhone(user.getWorkPhone());
    userDTO.setOrgName(user.getOrgName());
    userDTO.setPositionName(user.getPositionName());
    userDTO.setIsVisible(isVisible);
    userDTO.setSort(user.getSort());
    userDTO.setAvatarUrl("/rest/public/avatar/" + user.getId().toString());
    //@TODO сделать методы
    userDTO.setIsFavorite(favoriteDao.isFavorite(RestParamStorage.getCurrrentUserId(), user.getId()));
    return userDTO;
  }


  public List<UserDTO> convertUsers(List<User> users) {
    List<UserDTO> result = new ArrayList<>();
    List<Long> visibleUserIds = roleDao.visibleUserIds(RestParamStorage.getCurrrentUserId());
    for (User user : users) {
      //skip current user
      if (Objects.equals(user.getId(), RestParamStorage.getCurrrentUserId())) {
        continue;
      }

      result.add(convertUser(user, visibleUserIds.contains(user.getId())));
    }
    return result;
  }
}
