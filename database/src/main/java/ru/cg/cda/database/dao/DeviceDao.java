package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.Device;

/**
 * @author Badamshin
 */
public interface DeviceDao extends BaseDao<Device> {
  Device getByUdsIdOrName(String udsId, String name);

  Integer deleteAll();

  Integer deleteAllExcept(List<Long> skipIds);

  Device getByName(String name);
}
