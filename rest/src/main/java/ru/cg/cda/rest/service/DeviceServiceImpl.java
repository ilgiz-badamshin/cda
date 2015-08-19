package ru.cg.cda.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.Device;
import ru.cg.cda.database.dao.DeviceDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {
  @Autowired
  public DeviceDao deviceDao;

  @Override
  public Device getByName(String name) {
    return deviceDao.getByName(name);
  }


}
