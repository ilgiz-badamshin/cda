package ru.cg.cda.database.dao;

import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.Params;

/**
 * @author Badamshin
 */
@Repository
public class ParamsDaoImpl extends BaseDaoImpl<Params> implements ParamsDao {

  public static final Long PK = (long) 1;

  public Long getDbVersion() {
    Params params = get(PK);
    return params.getDbVersion();
  }

  public void increaseDbVersion() {
    Params params = get(PK);
    params.setDbVersion(params.getDbVersion() + 1);
    saveOrUpdate(params);
  }
}
