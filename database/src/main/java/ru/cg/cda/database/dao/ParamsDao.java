package ru.cg.cda.database.dao;

import ru.cg.cda.database.bean.Params;

/**
 * @author Badamshin
 */
public interface ParamsDao extends BaseDao<Params> {
  Long getDbVersion();

  void increaseDbVersion();
}
