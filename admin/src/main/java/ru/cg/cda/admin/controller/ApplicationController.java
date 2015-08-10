package ru.cg.cda.admin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.cg.cda.admin.service.ApplicationService;

@Controller
public class ApplicationController {

  @Autowired
  private ApplicationService applicationService;

  private Logger logger = Logger.getLogger(getClass().getName());

  public ApplicationController() {
  }

  @RequestMapping("/rest/config")
  public
  @ResponseBody
  Map<String, String> config() {
    Map<String, String> params = new HashMap<String, String>();

    params.put("projectName", applicationService.getProjectName());
    params.put("projectAuthor", applicationService.getProjectAuthor());
    params.put("projectWebsite", applicationService.getProjectWebsite());

    logger.info("Querying /config");

    return params;
  }
  @RequestMapping("/config")
  public
  @ResponseBody
  Map<String, String> config1() {
    Map<String, String> params = new HashMap<String, String>();

    params.put("projectName", applicationService.getProjectName());
    params.put("projectAuthor", applicationService.getProjectAuthor());
    params.put("projectWebsite", applicationService.getProjectWebsite());

    logger.info("Querying /config");

    return params;
  }


}
