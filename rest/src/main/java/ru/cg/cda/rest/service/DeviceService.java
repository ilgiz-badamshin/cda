package ru.cg.cda.rest.service;

import ru.cg.cda.database.bean.Device;

/**
 * @author Badamshin
 */
public interface DeviceService {
  Device getByName(String name);
}
