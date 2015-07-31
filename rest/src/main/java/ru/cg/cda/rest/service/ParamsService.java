package ru.cg.cda.rest.service;

/**
 * @author Badamshin
 */
public interface ParamsService {
  Long getDbVersion();

  void increaseDbVersion();
}
