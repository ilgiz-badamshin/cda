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

import ru.cg.cda.axl.beans.AxlDevice;
import ru.cg.cda.axl.service.AxlService;
import ru.cg.cda.database.bean.Device;
import ru.cg.cda.database.bean.User;
import ru.cg.cda.database.dao.DeviceDao;
import ru.cg.cda.database.dao.ParamsDao;
import ru.cg.cda.database.dao.UserDao;

/**
 * @author Badamshin
 */
@Transactional
@Component
public class DeviceSyncServiceImpl implements DeviceSyncService {
  Logger logger = LoggerFactory.getLogger(DeviceSyncServiceImpl.class);
  @Autowired
  private DeviceDao deviceDao;
  @Autowired
  private UserDao userDao;
  @Autowired
  private ParamsDao paramsDao;
  @Autowired
  private AxlService axlService;

  public void sync() {
    logger.info("Sync device start");
    List<AxlDevice> axlDevices = axlService.getDevices();
    List<Long> deviceIds = new ArrayList<>();
    Boolean updateDbVersion = false;
    for (AxlDevice axlDevice : axlDevices) {
      if (axlDevice.getUserId() == null) {
        logger.debug("Device without user. Device name {}, device ID {}", axlDevice.getName(), axlDevice.getId());
        continue;
      }
      Device device = deviceDao.getByUdsIdOrName(axlDevice.getId(), axlDevice.getName());
      if (device == null) {
        device = new Device();
        device.setInsertedAt(new Date());
        device.setUdsId(axlDevice.getId());
      }
      if (isDifferent(device, axlDevice)) {
        updateDbVersion = true;
        User user = userDao.getByUdsId(axlDevice.getUserId());
        if (user == null) {
          logger.debug("User for device not found. Device name {}, device ID {}, user Id {}", axlDevice.getName(), axlDevice.getId(), axlDevice.getUserId());
          continue;
        }
        device.setUserId(user.getId());
        device.setUpdatedAt(new Date());
        device.setDeleted(false);
        device.setUdsUserId(axlDevice.getUserId());
        device.setName(axlDevice.getName());
        device.setDescription(axlDevice.getDescription());
        device.setModel(axlDevice.getModel());
        deviceDao.saveOrUpdate(device);
        logger.debug("Sync device. Device name {}, device ID {}", axlDevice.getName(), axlDevice.getId());
      }
      else {
        logger.debug("Device skipped. Device name {}, device ID {}", axlDevice.getName(), axlDevice.getId());
      }
      deviceIds.add(device.getId());
    }

    Integer deletedCount;
    if (deviceIds.size() > 0) {
      deletedCount = deviceDao.deleteAllExcept(deviceIds);
    }
    else {
      deletedCount = deviceDao.deleteAll();
    }

    if (deletedCount > 0) {
      updateDbVersion = true;
      logger.debug("Deleted device count: {}", deletedCount);
    }
    if (updateDbVersion) {
      paramsDao.increaseDbVersion();
    }
  }

  private boolean isDifferent(Device device, AxlDevice axlDevice) {
    if (!ObjectUtils.equals(device.getUdsUserId(), axlDevice.getUserId())) {
      return true;
    }
    if (!ObjectUtils.equals(device.getName(), axlDevice.getName())) {
      return true;
    }
    if (!ObjectUtils.equals(device.getDescription(), axlDevice.getDescription())) {
      return true;
    }
    if (!ObjectUtils.equals(device.getModel(), axlDevice.getModel())) {
      return true;
    }
    if (device.getDeleted()) {
      return true;
    }
    return false;
  }
}
