package ru.cg.cda.admin.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.admin.dto.UserDTO;
import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.LinkUserRoleDao;
import ru.cg.cda.database.dao.UserDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;
  @Autowired
  private LinkUserRoleDao linkUserRoleDao;
  @Autowired
  private GroupService groupService;
  @Autowired
  private ParamsService paramsService;

  public List<UserDTO> getUsers() {
    return convertUsers(userDao.loadAll());
  }

  public UserDTO getUser(Long userId) {
    return convertUser(userDao.get(userId));
  }

  public List<Long> getUserRoleIds(Long userId) {
    return linkUserRoleDao.getRoleIds(userId);
  }

  public List<UserDTO> getUsersByGroup(Long groupId) {
    return convertUsers(userDao.byGroup(groupId));
  }

  public InputStream getAvatar(Long userId) {
    return getClass().getClassLoader().getResourceAsStream(String.format("ru/cg/cda/rest/avatar/%s.jpg", userId));
  }

  @Override
  public void setGroup(Long userId, Long groupId) {
    User user = userDao.get(userId);
    if (user != null) {
      user.setGroupId(groupId);
      userDao.saveOrUpdate(user);
      paramsService.increaseDbVersion();
    }
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
    userDTO.setLastName(user.getLastName());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setMiddleName(user.getMiddleName());
    userDTO.setVksNumber(user.getVksNumber());
    userDTO.setMobilePhone(user.getMobilePhone());
    userDTO.setWorkPhone(user.getWorkPhone());
    userDTO.setOrgName(user.getOrgName());
    userDTO.setPositionName(user.getPositionName());
    //@TODO убрать ИП из адреса
//    userDTO.setAvatarUrl("http://10.10.18.34:8080/rest/public/avatar/" + user.getId().toString());
    //@TODO сделать методы
//    userDTO.setIsFavorite(favoriteDao.isFavorite(RestParamStorage.getCurrrentUserId(), user.getId()));
    userDTO.setGroup(groupService.convertGroup(user.getGroup()));
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
