package ru.cg.cda.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.database.dao.RoleDao;
import ru.cg.cda.rest.service.ParamsService;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/dbversion")
public class DbversionController {

  @Autowired
  private RoleDao roleDao;
  @Autowired
  private ParamsService paramsService;

  /**
   * Возвращает список групп
   *
   * @return List<GroupDTO>
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public Long getDbVersion() {
    return paramsService.getDbVersion();
  }

}
