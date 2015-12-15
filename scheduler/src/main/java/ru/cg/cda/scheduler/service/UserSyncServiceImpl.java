package ru.cg.cda.scheduler.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.ParamsDao;
import ru.cg.cda.database.dao.UserDao;
import ru.cg.cda.uds.beans.UdsUser;
import ru.cg.cda.uds.service.UdsService;

/**
 * @author Badamshin
 */
@Transactional
@Component
public class UserSyncServiceImpl implements UserSyncService {

  private Logger logger = LoggerFactory.getLogger(UserSyncServiceImpl.class);

  @Autowired
  private UserDao userDao;

  @Autowired
  private ParamsDao paramsDao;

  @Autowired
  private UdsService udsService;

  public void sync() {
    logger.info("Sync user start");
    List<UdsUser> udsUsers = udsService.getUsers();
    List<Long> userIds = new ArrayList<>();
    Boolean updateDbVersion = false;
    for (UdsUser udsUser : udsUsers) {
      User user = userDao.getByUdsId(udsUser.getId());
      if (user == null) {
        user = new User();
        user.setInsertedAt(new Date());
        user.setUdsId(udsUser.getId());
      }
      if (isDifferent(user, udsUser)) {
        user.setUpdatedAt(new Date());
        user.setDeleted(false);
        user.setUserName(udsUser.getUserName());
        user.setFirstName(udsUser.getFirstName());
        user.setLastName(udsUser.getLastName());
        user.setMiddleName(udsUser.getMiddleName());
        user.setVksNumber(udsUser.getPhoneNumber());
        user.setMobilePhone(udsUser.getMobileNumber());
        user.setWorkPhone(udsUser.getHomeNumber());
        userDao.saveOrUpdate(user);
        logger.debug("Sync user. Username {}, userId  {}", udsUser.getUserName(), udsUser.getId());
      }
      else {
        logger.debug("User skipped. Username {}, userId  {}", udsUser.getUserName(), udsUser.getId());
      }
      userIds.add(user.getId());
    }

    Integer deletedCount;
    if (userIds.size() > 0) {
      deletedCount = userDao.deleteAllExcept(userIds);
    }
    else {
      deletedCount = userDao.deleteAll();
    }

    if (deletedCount > 0) {
      updateDbVersion = true;
      logger.debug("Deleted user count: {}", deletedCount);
    }
    if (updateDbVersion) {
      paramsDao.increaseDbVersion();
    }
  }

  private boolean isDifferent(User user, UdsUser udsUser) {
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
    if (user.getDeleted()) {
      return true;
    }
    return false;
  }
}
