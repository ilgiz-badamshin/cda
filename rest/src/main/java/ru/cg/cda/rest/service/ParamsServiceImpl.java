package ru.cg.cda.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.cg.cda.database.dao.ParamsDao;

/**
 * @author Badamshin
 */
@Component
@Transactional
public class ParamsServiceImpl implements ParamsService {

  @Autowired
  private ParamsDao paramsDao;

  @Override
  public Long getDbVersion() {
    return paramsDao.getDbVersion();
  }

  @Override
  public void increaseDbVersion() {
    paramsDao.increaseDbVersion();
  }
}
