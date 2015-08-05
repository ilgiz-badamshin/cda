package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.Device;

/**
 * @author Badamshin
 */
public interface DeviceDao extends BaseDao<Device> {
  Device getByUdsId(String udsId);

  Integer deleteAll();

  Integer deleteAllExcept(List<Long> skipIds);
}
