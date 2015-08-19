package ru.cg.cda.admin.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

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
  @Value("${avatarsFolder}")
  private String AVATARS_FOLDER;

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


  @Override
  public void setGroup(Long userId, Long groupId) {
    User user = userDao.get(userId);
    if (user != null) {
      user.setGroupId(groupId);
      userDao.saveOrUpdate(user);
      paramsService.increaseDbVersion();
    }
  }

  @Override
  public void setOrgName(Long userId, String orgName) {
    User user = userDao.get(userId);
    if (user != null) {
      user.setOrgName(orgName);
      userDao.saveOrUpdate(user);
      paramsService.increaseDbVersion();
    }
  }

  @Override
  public void setPositionName(Long userId, String positionName) {
    User user = userDao.get(userId);
    if (user != null) {
      user.setPositionName(positionName);
      userDao.saveOrUpdate(user);
      paramsService.increaseDbVersion();
    }
  }

  @Override
  public File getAvatar(Long userId) {
    return new File(AVATARS_FOLDER, userId + ".png");
  }

  @Override
  public void saveAvatar(Long userId, String base64) {
    base64 = base64.substring(base64.indexOf(",") + 1);

    BufferedImage bufferedImage;
    byte[] imageByte;
    try {
      BASE64Decoder decoder = new BASE64Decoder();
      imageByte = decoder.decodeBuffer(base64);
      ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
      bufferedImage = ImageIO.read(bis);
      bis.close();
      ImageIO.write(bufferedImage, "png", new File(AVATARS_FOLDER, userId + ".png"));
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Boolean deleteAvatar(Long userId) {
    File file = new File(AVATARS_FOLDER, userId + ".png");
    return !file.exists() || file.delete();
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
    userDTO.setDeleted(user.getDeleted());
    //@TODO убрать ИП из адреса
    userDTO.setAvatarUrl(MessageFormat.format("/admin/rest/user/{0}/avatar/", user.getId()));
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
