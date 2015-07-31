package ru.cg.cda.scheduler.service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.UserDao;
import ru.cg.cda.uds.beans.UdsUser;
import ru.cg.cda.uds.service.UdsService;

/**
 * @author Badamshin
 */
@Transactional
@Component
public class UserSyncServiceImpl implements UserSyncService {
  Logger logger = LoggerFactory.getLogger(UserSyncServiceImpl.class);
  @Autowired
  private UserDao userDao;

  @Autowired
  private UdsService udsService;

  public void sync() {
    logger.debug("Sync user start");
    List<UdsUser> udsUsers = udsService.getUsers();
    for (UdsUser udsUser : udsUsers) {
      User user = userDao.getByUdsId(udsUser.getId());
      if (user == null) {
        user = new User();
        user.setInsertedAt(new Date());
      }
      if (isDifferent(user, udsUser)) {
        user.setUpdatedAt(new Date());
        user.setUdsId(udsUser.getId());
        user.setUserName(udsUser.getUserName());
        user.setFirstName(udsUser.getFirstName());
        user.setLastName(udsUser.getLastName());
        user.setMiddleName(udsUser.getMiddleName());
        user.setVksNumber(udsUser.getPhoneNumber());
        user.setMobilePhone(udsUser.getMobileNumber());
        user.setWorkPhone(udsUser.getHomeNumber());
        userDao.saveOrUpdate(user);
        logger.debug("Sync user {} - {}", udsUser.getUserName(), udsUser.getId());
      }
      else {
        logger.debug("User skipped {} - {}", udsUser.getUserName(), udsUser.getId());
      }
    }
  }

  private boolean isDifferent(User user, UdsUser udsUser) {
    if (!ObjectUtils.equals(user.getUdsId(), udsUser.getId())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getUserName(), udsUser.getUserName())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getFirstName(), udsUser.getFirstName())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getLastName(), udsUser.getLastName())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getMiddleName(), udsUser.getMiddleName())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getVksNumber(), udsUser.getPhoneNumber())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getMobilePhone(), udsUser.getMobileNumber())) {
      return true;
    }
    if (!ObjectUtils.equals(user.getWorkPhone(), udsUser.getHomeNumber())) {
      return true;
    }
    return false;
  }
}
