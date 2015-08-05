package ru.cg.cda.database.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.Device;

/**
 * @author Badamshin
 */
@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Device> implements DeviceDao {
  @Override
  public Device getByUdsId(String udsId) {
    return (Device) create().add(Restrictions.eq("udsId", udsId).ignoreCase()).uniqueResult();
  }

  @Override
  public Integer deleteAll() {
    Query query = session().createQuery("update Device set deleted = true where deleted != true");
    return query.executeUpdate();
  }

  @Override
  public Integer deleteAllExcept(List<Long> skipIds) {
    Query query = session().createQuery("update Device set deleted = true where deleted != true AND id  NOT IN :skipIds");
    query.setParameterList("skipIds", skipIds);
    return query.executeUpdate();
  }
}
