package ru.cg.cda.admin.service;

/**
 * @author Badamshin
 */
public interface ParamsService {
  Long getDbVersion();

  void increaseDbVersion();
}
